package govhack.thecodefather.patentpending.presentation.adapter;

import android.content.Context;
import android.graphics.PorterDuff.Mode;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import govhack.thecodefather.patentpending.R;
import govhack.thecodefather.patentpending.data.enums.PatentStage;
import govhack.thecodefather.patentpending.data.models.StageDataModel;
import govhack.thecodefather.patentpending.utility.ColorUtility;
import java.util.List;
import java.util.Random;
import lombok.Getter;
import lombok.val;

/**
 * Created by Alberto Camillo on 29/7/17.
 */
public class HorizontalStagesAdapter
    extends RecyclerView.Adapter<HorizontalStagesAdapter.StageViewHolder> {

  @Getter
  private final Context context;
  private List<StageDataModel> stages;

  public HorizontalStagesAdapter(List<StageDataModel> stages, Context context) {
    this.stages = stages;
    this.context = context;
  }

  @Override
  public HorizontalStagesAdapter.StageViewHolder onCreateViewHolder(
      ViewGroup parent, int viewType) {
    View v =
        LayoutInflater.from(parent.getContext())
            .inflate(R.layout.recyclerview_patent_status_item, parent, false);
    StageViewHolder pvh = new StageViewHolder(v);
    return pvh;
  }

  @Override
  public void onBindViewHolder(HorizontalStagesAdapter.StageViewHolder holder, int position) {
    val patentModel = stages.get(position);
    // Patent status label
    holder.tvPatentStatus.setText(patentModel.getStage().toString());
    if (getContext() != null) {
      val color = ColorUtility.getColor(getContext(), patentModel.getStage().getColorResId());
      holder.flPatentLabel.getBackground().setColorFilter(color, Mode.DST_ATOP);
    }

      holder.tvPatentEstimatedDateFinish.setVisibility(View.VISIBLE);
    if (patentModel.getStage() == PatentStage.EXPIRED || patentModel.getStage() == PatentStage.REGISTERED) {
      holder.tvPatentEstimatedDateFinish.setVisibility(View.GONE);
    } else {
      holder.tvPatentEstimatedDateFinish.setVisibility(View.VISIBLE);
      holder.tvPatentEstimatedDateFinish.setText(String.format("est. %d days to go",
          new Random().nextInt(600) + 1));
    }
  }

  @Override
  public int getItemCount() {
    return stages.size();
  }

  @Override
  public void onAttachedToRecyclerView(RecyclerView recyclerView) {
    super.onAttachedToRecyclerView(recyclerView);
  }

  public class StageViewHolder extends RecyclerView.ViewHolder {

    private TextView tvPatentStatus;
    private TextView tvPatentEstimatedDateFinish;
    private FrameLayout flPatentLabel;

    public StageViewHolder(View itemView) {
      super(itemView);
      tvPatentStatus = (TextView) itemView.findViewById(R.id.tvPatentStatus);
      tvPatentEstimatedDateFinish =
          (TextView) itemView.findViewById(R.id.tvPatentEstimatedDateFinish);
      flPatentLabel = (FrameLayout) itemView.findViewById(R.id.flPatentLabel);
    }
  }
}
