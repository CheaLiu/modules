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

    private int count = 30;

    @Override
    public RecyclerView.ViewHolder onNormalCreateViewHolder(ViewGroup parent) {
        return new ViewHolder(new TextView(parent.getContext()));
    }

    @Override
    protected void onBindNormalViewHolder(RecyclerView.ViewHolder holder, int position) {
        TextView itemView = (TextView) holder.itemView;
        itemView.setText(position + "---------" + position);
    }

    @Override
    protected void onBindFooterViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    protected void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    public void addItems() {
        int lastCount = count;
        count += 10;
        notifyItemRangeInserted(lastCount, 10);
    }

    @Override
    public int getDataCount() {
        return count;
    }
}
