package govhack.thecodefather.patentpending.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import govhack.thecodefather.patentpending.R;

/**
 * Created by Alberto Camillo on 29/7/17.
 */

public class RecyclerViewSearchAdapter extends RecyclerView.Adapter<RecyclerViewSearchAdapter.PatentViewHolder> {

    @Override
    public PatentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(PatentViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
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
