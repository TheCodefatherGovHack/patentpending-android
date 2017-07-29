package govhack.thecodefather.patentpending.presentation.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.common.collect.ImmutableList;
import com.google.gson.Gson;

import java.util.List;

import govhack.thecodefather.patentpending.R;
import govhack.thecodefather.patentpending.data.models.PatentDataModel;
import govhack.thecodefather.patentpending.presentation.PatentDetailActivity_;

import static android.content.ContentValues.TAG;
import static govhack.thecodefather.patentpending.Constants.SELECTED_PATENT;

/** Created by Alberto Camillo on 29/7/17. */
public class RecyclerViewSearchAdapter
    extends RecyclerView.Adapter<RecyclerViewSearchAdapter.PatentViewHolder> {

  private List<PatentDataModel> patents;

  public RecyclerViewSearchAdapter(List<PatentDataModel> patents) {
    this.patents = patents;
  }

  @Override
  public PatentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View v =
        LayoutInflater.from(parent.getContext())
            .inflate(R.layout.recyclerview_patent_item, parent, false);
    PatentViewHolder pvh = new PatentViewHolder(v);
    return pvh;
  }

  @Override
  public void onBindViewHolder(PatentViewHolder holder, int position) {
    holder.tvPatentTitle.setText(patents.get(position).getTitle());
    holder.tvPatentStatus.setText(patents.get(position).getTitle());
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

  public class PatentViewHolder extends RecyclerView.ViewHolder {

    private CardView cvPatent;
    private TextView tvPatentTitle;
    private TextView tvPatentStatus;
    private TextView tvPatentNextEvent;
    private PatentDataModel selectedPatent;

    PatentViewHolder(View itemView) {
      super(itemView);
      cvPatent = (CardView) itemView.findViewById(R.id.cvPatent);
      tvPatentTitle = (TextView) itemView.findViewById(R.id.tvPatentTitle);
      tvPatentStatus = (TextView) itemView.findViewById(R.id.tvPatentStatus);
      tvPatentNextEvent = (TextView) itemView.findViewById(R.id.tvPatentNextEvent);

      final Gson gson = new Gson();

      itemView.setOnClickListener(
          new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Context context = v.getContext();
              Log.d(
                  TAG,
                  "onClick "
                      + getAdapterPosition()
                      + " "
                      + patents.get(getAdapterPosition()).getTitle());
              final Intent i = new Intent(context, PatentDetailActivity_.class);
              String userJson = gson.toJson(patents.get(getAdapterPosition()));

              i.putExtra(SELECTED_PATENT, userJson);
              context.startActivity(i);
            }
          });
    }
  }
}
