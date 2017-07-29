package govhack.thecodefather.patentpending.presentation.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.common.collect.ImmutableList;
import govhack.thecodefather.patentpending.R;
import govhack.thecodefather.patentpending.data.models.PatentDataModel;
import java.util.List;
import lombok.val;

/**
 * Created by Alberto Camillo on 29/7/17.
 */

public class RecyclerViewSearchAdapter extends
    RecyclerView.Adapter<RecyclerViewSearchAdapter.PatentViewHolder> {

  List<PatentDataModel> patents;

  public RecyclerViewSearchAdapter(List<PatentDataModel> patents) {
    this.patents = patents;
  }

  @Override
  public PatentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View v = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.recyclerview_patent_item, parent, false);
    PatentViewHolder pvh = new PatentViewHolder(v);
    return pvh;
  }

  @Override
  public void onBindViewHolder(PatentViewHolder holder, int position) {
    val patentModel = patents.get(position);
    holder.tvPatentTitle.setText(patentModel.getTitle());
    holder.tvPatentStatus.setText(patentModel.getCurrentStage().toString());
    holder.tvPatentNextStage.setText(patentModel.nextStageDisplayString());
  }

  @Override
  public int getItemCount() {
    return patents.size();
  }

  @Override
  public void onAttachedToRecyclerView(RecyclerView recyclerView) {
    super.onAttachedToRecyclerView(recyclerView);
  }

  public void updatePatents(@Nullable ImmutableList<PatentDataModel> newPatents) {
    patents.clear();
    if (null != newPatents && !newPatents.isEmpty()) {
      patents.addAll(newPatents);
    }
    notifyDataSetChanged();
  }

  public static class PatentViewHolder extends RecyclerView.ViewHolder {

    CardView cvPatent;
    TextView tvPatentTitle;
    ImageView ivNotification;
    TextView tvPatentStatus;
    TextView tvPatentNextStage;

    PatentViewHolder(View itemView) {
      super(itemView);
      cvPatent = (CardView) itemView.findViewById(R.id.cvPatent);
      tvPatentTitle = (TextView) itemView.findViewById(R.id.tvPatentTitle);
      ivNotification = (ImageView) itemView.findViewById(R.id.ivNotification);
      tvPatentStatus = (TextView) itemView.findViewById(R.id.tvPatentStatus);
      tvPatentNextStage = (TextView) itemView.findViewById(R.id.tvPatentNextStage);
    }
  }
}
