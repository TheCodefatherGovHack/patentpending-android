package govhack.thecodefather.patentpending.presentation;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.common.collect.ImmutableList;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Fullscreen;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

import govhack.thecodefather.patentpending.R;
import govhack.thecodefather.patentpending.data.api.ApiClientFactory;
import govhack.thecodefather.patentpending.data.api.HttpCallback;
import govhack.thecodefather.patentpending.data.models.GodDataModel;
import govhack.thecodefather.patentpending.data.models.PatentDataModel;
import govhack.thecodefather.patentpending.presentation.adapter.RecyclerViewSearchAdapter;
import retrofit2.Call;
import retrofit2.Response;

@Fullscreen
@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    private RecyclerViewSearchAdapter rvSearchAdapter;
    private List<PatentDataModel> mPatents = new ArrayList<>();

    @ViewById
    RecyclerView rvSearchResults;

    @AfterViews
    void initPatentsList() {

        rvSearchAdapter = new RecyclerViewSearchAdapter(mPatents);
        rvSearchResults.setAdapter(rvSearchAdapter);
        rvSearchResults.setLayoutManager(new LinearLayoutManager(this));
        searchPatentsAsync("");

    }

    @Background
    void searchPatentsAsync(String searchString) {
        ApiClientFactory.getPatentPending().getGod(searchString).enqueue(new HttpCallback<GodDataModel>() {
            @Override
            public void onSuccess(Call<GodDataModel> call, Response<GodDataModel> response) {
                updatePatents(response.body().getPatents());
            }

            @Override
            public void onError(Call<GodDataModel> call, Response<GodDataModel> response) {
                Log.d("getGod", "onError");
            }

            @Override
            public void onFailure(Call<GodDataModel> call) {
                Log.d("getGod", "onFailure");
            }
        });
    }

    @UiThread
    void updatePatents(ImmutableList<PatentDataModel> patents) {
        rvSearchAdapter.updatePatents(patents);
    }

}
