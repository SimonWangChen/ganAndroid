package com.proclassmates.ganandroid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.zip.Inflater;

public class GreenAdapter extends RecyclerView.Adapter<GreenAdapter.NumberViewHolder> {
    final private ListItemClickListener mlistItemClickListener;

    int viewHolderCount;

    int mNumberItems;

    public GreenAdapter(ListItemClickListener listItemClickListener, int numberItems) {
        this.mlistItemClickListener = listItemClickListener;
        mNumberItems = numberItems;
        viewHolderCount = 0;
    }

    public interface ListItemClickListener{
        void onListItemClick(int index);
    }

    public class NumberViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView listItemNumberView;
        TextView viewHolderIndex;

        public NumberViewHolder(@NonNull View itemView) {
            super(itemView);
            listItemNumberView = itemView.findViewById(R.id.tv_item_num);
            viewHolderIndex = itemView.findViewById(R.id.tv_view_holder_instance);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int clickedPosition = getAdapterPosition();
            mlistItemClickListener.onListItemClick(clickedPosition);
        }

        void bind(int position){
            listItemNumberView.setText(String.valueOf(position));
        }
    }

    @NonNull
    @Override
    public NumberViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        NumberViewHolder numberViewHolder;
        Context recyclerViewContext = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(recyclerViewContext);

        int layoutIdForHolder = R.layout.num_list_item;

        boolean shouldAttachToParentImmediately = false;

        View recyclerView = layoutInflater.inflate(layoutIdForHolder, parent, shouldAttachToParentImmediately);

        numberViewHolder = new NumberViewHolder(recyclerView);

        numberViewHolder.viewHolderIndex.setText("view holder index:" + viewHolderCount);

        int backgroundColorForViewHolder = ColorUtils
                .getViewHolderBackgroundColorFromInstance(recyclerViewContext, viewHolderCount);
        numberViewHolder.itemView.setBackgroundColor(backgroundColorForViewHolder);

        viewHolderCount++;

        return numberViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NumberViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return mNumberItems;
    }
}
