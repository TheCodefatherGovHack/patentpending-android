package govhack.thecodefather.patentpending.presentation.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import govhack.thecodefather.patentpending.R;
import govhack.thecodefather.patentpending.data.models.PatentDataModel;

/**
 * Created by Alberto Camillo on 29/7/17.
 */

public class RecyclerViewSearchAdapter extends RecyclerView.Adapter<RecyclerViewSearchAdapter.PatentViewHolder> {

    List<PatentDataModel> messages;

    public RecyclerViewSearchAdapter(List<PatentDataModel> messages) {
        this.messages = messages;
    }

    @Override
    public PatentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_patent_item, parent, false);
        PatentViewHolder pvh = new PatentViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(PatentViewHolder holder, int position) {
        holder.tvPatentTitle.setText(messages.get(position).getTitle());
        holder.tvPatentStatus.setText(messages.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public static class PatentViewHolder extends RecyclerView.ViewHolder {

        CardView cvPatent;
        TextView tvPatentTitle;
        TextView tvPatentStatus;
        TextView tvPatentNextEvent;

        PatentViewHolder(View itemView) {
            super(itemView);
            cvPatent = (CardView) itemView.findViewById(R.id.cvPatent);
            tvPatentTitle = (TextView) itemView.findViewById(R.id.tvPatentTitle);
            tvPatentStatus = (TextView) itemView.findViewById(R.id.tvPatentStatus);
            tvPatentNextEvent = (TextView) itemView.findViewById(R.id.tvPatentNextEvent);
        }
    }
}
