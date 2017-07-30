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
import android.widget.ImageView;
import android.widget.TextView;

import com.google.common.collect.ImmutableList;
import com.google.gson.Gson;

import java.util.List;

import govhack.thecodefather.patentpending.R;
import govhack.thecodefather.patentpending.data.models.PatentDataModel;
import govhack.thecodefather.patentpending.presentation.PatentDetailActivity_;
import lombok.val;

import static android.content.ContentValues.TAG;
import static govhack.thecodefather.patentpending.Constants.SELECTED_PATENT;

/**
 * Created by Alberto Camillo on 29/7/17.
 */

public class RecyclerViewSearchAdapter extends
        RecyclerView.Adapter<RecyclerViewSearchAdapter.PatentViewHolder> {

    List<PatentDataModel> mPatents;

    public RecyclerViewSearchAdapter(List<PatentDataModel> mPatents) {
        this.mPatents = mPatents;
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
        val patentModel = mPatents.get(position);
        holder.tvPatentTitle.setText(patentModel.getTitle());
        holder.tvPatentStatus.setText(patentModel.getCurrentStage().toString());
        holder.tvPatentNextStage.setText(patentModel.nextStageDisplayString());
    }

    @Override
    public int getItemCount() {
        return mPatents.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public void updatePatents(@Nullable ImmutableList<PatentDataModel> newPatents) {
        mPatents.clear();
        if (null != newPatents && !newPatents.isEmpty()) {
            mPatents.addAll(newPatents);
        }
        notifyDataSetChanged();
    }


    public class PatentViewHolder extends RecyclerView.ViewHolder {

        CardView cvPatent;
        TextView tvPatentTitle;
        ImageView ivNotification;
        TextView tvPatentStatus;
        TextView tvPatentNextStage;

        PatentViewHolder(View itemView) {
            super(itemView);
            cvPatent = (CardView) itemView.findViewById(R.id.cvPatent);
            tvPatentTitle = (TextView) itemView.findViewById(R.id.tvPatentTitle);
            tvPatentStatus = (TextView) itemView.findViewById(R.id.tvPatentStatus);

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
                                            + mPatents.get(getAdapterPosition()).getTitle());
                            final Intent i = new Intent(context, PatentDetailActivity_.class);
                            String userJson = gson.toJson(mPatents.get(getAdapterPosition()));

                            i.putExtra(SELECTED_PATENT, userJson);
                            context.startActivity(i);
                        }
                    });
            ivNotification = (ImageView) itemView.findViewById(R.id.ivNotification);
            tvPatentStatus = (TextView) itemView.findViewById(R.id.tvPatentStatus);
            tvPatentNextStage = (TextView) itemView.findViewById(R.id.tvPatentNextStage);
        }
    }
}
