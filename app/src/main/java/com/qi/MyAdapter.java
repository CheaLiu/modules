package com.qi;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Creator  liuqi
 * Data     2018/4/25
 * Class    com.qi.MyAdapter
 */
public class MyAdapter extends RefreshAdapter {
    private ArrayList<String> data;

    public MyAdapter(ArrayList<String> data) {
        this.data = data;
    }

    @Override
    public RecyclerView.ViewHolder onNormalCreateViewHolder(ViewGroup parent) {
        return new ViewHolder(new TextView(parent.getContext()));
    }

    @Override
    protected void onBindNormalViewHolder(RecyclerView.ViewHolder holder, int position) {
        TextView itemView = (TextView) holder.itemView;
        itemView.setPadding(30,30,30,30);
        itemView.setText(data.get(position) + "---------" + position);
    }

    @Override
    protected void onBindFooterViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    protected void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getDataCount() {
        return data.size();
    }
}
