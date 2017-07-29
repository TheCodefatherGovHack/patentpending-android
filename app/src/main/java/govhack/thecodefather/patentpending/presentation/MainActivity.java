package govhack.thecodefather.patentpending.presentation;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

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
import lombok.val;
import retrofit2.Call;
import retrofit2.Response;

import static android.support.design.widget.Snackbar.make;

@Fullscreen
@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    private RecyclerViewSearchAdapter rvSearchAdapter;
    private List<PatentDataModel> mPatents = new ArrayList<>();

    @ViewById(R.id.root_view)
    CoordinatorLayout rootView;

    @ViewById
    RecyclerView rvSearchResults;

    @AfterViews
    void initPatentsList() {
        rvSearchAdapter = new RecyclerViewSearchAdapter(mPatents);
        rvSearchResults.setAdapter(rvSearchAdapter);
        rvSearchResults.setLayoutManager(new LinearLayoutManager(this));
        submitSearchRequest("");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        val inflater = getMenuInflater();
        inflater.inflate(R.menu.main_search_view, menu);
        MenuItem searchViewItem = menu.findItem(R.id.action_search);

        val searchViewAndroidActionBar = (SearchView) MenuItemCompat.getActionView(searchViewItem);

        searchViewAndroidActionBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchViewAndroidActionBar.clearFocus();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                submitSearchRequest(newText);
                return true;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    private void submitSearchRequest(@Nullable String query) {
        if (!TextUtils.isEmpty(query)) {
            ApiClientFactory.getPatentPending().getGod(query).enqueue(new HttpCallback<GodDataModel>() {
                @Override
                public void onSuccess(Call<GodDataModel> call, Response<GodDataModel> response) {
                    updatePatents(response.body().getPatents());
                }

                @Override
                public void onError(Call<GodDataModel> call, Response<GodDataModel> response) {
                    showSnackbar("Uh-oh an error occurred, please retry");
                }

                @Override
                public void onFailure(Call<GodDataModel> call) {
                    showSnackbar("Uh-oh an error occurred, please retry");
                }
            });
        }else{
            updatePatents(null);
        }
    }

    @UiThread
    void updatePatents(@Nullable ImmutableList<PatentDataModel> patents) {
        rvSearchAdapter.updatePatents(patents);
    }


    private void showSnackbar(@NonNull String s) {
        showSnackbar(s, Snackbar.LENGTH_LONG);
    }

    private void showSnackbar(@NonNull String s, int duration) {
        Snackbar snackbar = make(rootView, s, duration);
        snackbar.show();
    }

}
