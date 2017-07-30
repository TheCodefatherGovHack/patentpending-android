package govhack.thecodefather.patentpending.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;

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

        LinearSnapHelper snapHelper = new LinearSnapHelper() {
            @Override
            public int findTargetSnapPosition(RecyclerView.LayoutManager layoutManager, int velocityX, int velocityY) {
                View centerView = findSnapView(layoutManager);
                if (centerView == null)
                    return RecyclerView.NO_POSITION;

                int position = layoutManager.getPosition(centerView);
                int targetPosition = -1;
                if (layoutManager.canScrollHorizontally()) {
                    if (velocityX < 0) {
                        targetPosition = position - 1;
                    } else {
                        targetPosition = position + 1;
                    }
                }

                if (layoutManager.canScrollVertically()) {
                    if (velocityY < 0) {
                        targetPosition = position - 1;
                    } else {
                        targetPosition = position + 1;
                    }
                }

                final int firstItem = 0;
                final int lastItem = layoutManager.getItemCount() - 1;
                targetPosition = Math.min(lastItem, Math.max(targetPosition, firstItem));
                return targetPosition;
            }
        };
        snapHelper.attachToRecyclerView(rvPatentStatuses);

    }
}
