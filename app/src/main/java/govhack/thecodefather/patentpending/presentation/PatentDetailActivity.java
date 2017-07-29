package govhack.thecodefather.patentpending.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Fullscreen;
import org.androidannotations.annotations.ViewById;

import govhack.thecodefather.patentpending.R;
import govhack.thecodefather.patentpending.data.models.PatentDataModel;
import govhack.thecodefather.patentpending.presentation.adapter.HorizontalStagesAdapter;

import static govhack.thecodefather.patentpending.Constants.SELECTED_PATENT;

/** Created by Alberto Camillo on 29/7/17. */
@Fullscreen
@EActivity(R.layout.activity_patent_detail)
public class PatentDetailActivity extends AppCompatActivity {
    @ViewById
    RecyclerView rvPatentStatuses;
    private HorizontalStagesAdapter horizontalStagesAdapter;

    private PatentDataModel mSelectedPatent;
    private final Gson gson = new Gson();

    @AfterViews
    void initPatentsDetails() {
        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        String serializedPatent = bundle.getString(SELECTED_PATENT);
        mSelectedPatent = gson.fromJson(serializedPatent, PatentDataModel.class);

        LinearLayoutManager horizontalLayoutManagaer = new LinearLayoutManager(PatentDetailActivity.this, LinearLayoutManager.HORIZONTAL, false);
        horizontalStagesAdapter = new HorizontalStagesAdapter(mSelectedPatent.getStages());
        rvPatentStatuses.setAdapter(horizontalStagesAdapter);
        rvPatentStatuses.setLayoutManager(horizontalLayoutManagaer);
    }
}
