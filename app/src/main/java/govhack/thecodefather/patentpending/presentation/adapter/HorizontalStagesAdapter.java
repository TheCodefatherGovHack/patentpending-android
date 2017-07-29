package govhack.thecodefather.patentpending.presentation.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import govhack.thecodefather.patentpending.R;
import govhack.thecodefather.patentpending.data.models.StageDataModel;

/** Created by Alberto Camillo on 29/7/17. */
public class HorizontalStagesAdapter
    extends RecyclerView.Adapter<HorizontalStagesAdapter.StageViewHolder> {
  private List<StageDataModel> stages;

  public HorizontalStagesAdapter(List<StageDataModel> stages) {
    this.stages = stages;
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
    holder.tvPatentStatus.setText(stages.get(position).getStageName().toString());
    //holder.tvPatentEstimatedDateFinish.setText(stages.get(position).getEstimatedDateOfFinish().toString());
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

    public StageViewHolder(View itemView) {
      super(itemView);
      tvPatentStatus = (TextView) itemView.findViewById(R.id.tvPatentStatus);
      tvPatentEstimatedDateFinish =
          (TextView) itemView.findViewById(R.id.tvPatentEstimatedDateFinish);
    }
  }
}
