package com.proclassmates.ganandroid.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.proclassmates.ganandroid.R;

import java.util.List;

/**
 * @name: GanAndroid
 * @desc:
 * @class name:com.proclassmates.ganandroid.adapter
 * @author: simon
 * @time： 2019-11-18 18:11
 */

public class SimpleListAdapter extends RecyclerView.Adapter<MyViewHolder> {

    private LayoutInflater mInflater;
    private Context mContext;
    private List<String> mDatas;

    public SimpleListAdapter(Context context, List<String> datas) {
        this.mContext = context;
        this.mDatas = datas;
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_single_textview, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tv.setText(mDatas.get(position));
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }
}

class MyViewHolder extends RecyclerView.ViewHolder {
    TextView tv;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        tv = itemView.findViewById(R.id.tv_single_item);
    }
}