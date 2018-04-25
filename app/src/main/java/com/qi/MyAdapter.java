package com.qi;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Creator  liuqi
 * Data     2018/4/25
 * Class    com.qi.MyAdapter
 */
public class MyAdapter extends RefreshAdapter {
    private String[] data;

    public MyAdapter(String[] data) {
        this.data = data;
    }

    @Override
    public RecyclerView.ViewHolder onNormalCreateViewHolder(ViewGroup parent) {
        return new ViewHolder(new TextView(parent.getContext()));
    }

    @Override
    protected void onBindNormalViewHolder(RecyclerView.ViewHolder holder, int position) {
        TextView itemView = (TextView) holder.itemView;
        itemView.setText(data[position] + "---------" + position);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notifyDataSetChanged_override();
            }
        });
    }

    @Override
    protected void onBindFooterViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    protected void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getDataCount() {
        return data.length;
    }
}
