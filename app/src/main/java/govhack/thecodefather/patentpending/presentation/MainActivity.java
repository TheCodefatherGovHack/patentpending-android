package govhack.thecodefather.patentpending.presentation;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Fullscreen;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

import govhack.thecodefather.patentpending.R;
import govhack.thecodefather.patentpending.data.models.PatentDataModel;
import govhack.thecodefather.patentpending.presentation.adapter.RecyclerViewSearchAdapter;

@Fullscreen
@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    RecyclerViewSearchAdapter rvSearchAdapter;

    @ViewById
    RecyclerView rvSearchResults;

    @AfterViews
    void initMessagesList() {

        List<PatentDataModel> messages = new ArrayList<>();

        rvSearchAdapter = new RecyclerViewSearchAdapter(messages);
        rvSearchResults.setAdapter(rvSearchAdapter);
    }

}
