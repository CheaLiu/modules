package com.qi;

import android.support.v7.widget.RecyclerView;
import android.view.FocusFinder;
import android.view.View;
import android.view.ViewGroup;

/**
 * Creator  liuqi
 * Data     2018/4/24
 * Class    com.qi.RefreshAdapter
 */
public abstract class RefreshAdapter extends RecyclerView.Adapter {

    private View headerView;
    private View footerView;
    public static final int HEADER_TYPE = 1;
    public static final int FOOTER_TYPE = 2;

    public void setHeader(View header) {
        this.headerView = header;
        notifyItemChanged(0);
    }

    public void setFooter(View footerView) {
        this.footerView = footerView;
        notifyItemChanged(getItemCount() - 1);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == HEADER_TYPE)
            return new ViewHolder(headerView);
        else if (viewType == FOOTER_TYPE)
            return new ViewHolder(footerView);
        return onNormalCreateViewHolder(parent);
    }

    public abstract RecyclerView.ViewHolder onNormalCreateViewHolder(ViewGroup parent);

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == HEADER_TYPE)
            onBindHeaderViewHolder(holder, position);
        else if (getItemViewType(position) == FOOTER_TYPE)
            onBindFooterViewHolder(holder, position);
        else {
            if (headerView!=null){
                position = position-1;
            }
            onBindNormalViewHolder(holder, position);
        }
    }

    protected abstract void onBindNormalViewHolder(RecyclerView.ViewHolder holder, int position);

    protected abstract void onBindFooterViewHolder(RecyclerView.ViewHolder holder, int position);

    protected abstract void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int position);

    @Override
    public int getItemCount() {
        int count = getDataCount();
        if (headerView != null) {
            count += 1;
        }
        if (footerView != null) {
            count += 1;
        }
        return count;
    }

    public abstract int getDataCount();

    @Override
    public int getItemViewType(int position) {
        if (headerView != null && position == 0) {
            return HEADER_TYPE;
        } else if (footerView != null && position == getItemCount() - 1) {
            return FOOTER_TYPE;
        }
        return super.getItemViewType(position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
