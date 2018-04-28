package com.qi;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Creator  liuqi
 * Data     2018/4/24
 * Class    com.qi.RefreshAdapter
 */
public abstract class RefreshAdapter extends RecyclerView.Adapter {

    private View mHeaderView;
    private View mFooterView;
    private static final int HEADER_TYPE = 1;
    private static final int FOOTER_TYPE = 2;

    public void setHeader(View headerView) {
        if (mHeaderView == headerView) {
            return;
        }
        this.mHeaderView = headerView;
        notifyItemChanged(0);
    }

    public void setFooter(View footerView) {
        if (this.mFooterView == footerView) {
            return;
        }
        this.mFooterView = footerView;
        notifyItemChanged(getItemCount() - 1);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == HEADER_TYPE)
            return new ViewHolder(mHeaderView);
        else if (viewType == FOOTER_TYPE)
            return new ViewHolder(mFooterView);
        return onNormalCreateViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == HEADER_TYPE)
            onBindHeaderViewHolder(holder, position);
        else if (getItemViewType(position) == FOOTER_TYPE)
            onBindFooterViewHolder(holder, position);
        else {
            if (mHeaderView != null) {
                position = position - 1;
            }
            onBindNormalViewHolder(holder, position);
        }
    }

    public abstract RecyclerView.ViewHolder onNormalCreateViewHolder(ViewGroup parent);

    /**
     * 数据条目展示
     *
     * @param holder
     * @param position
     */
    protected abstract void onBindNormalViewHolder(RecyclerView.ViewHolder holder, int position);

    /**
     * mFooterView
     *
     * @param holder
     * @param position
     */
    protected abstract void onBindFooterViewHolder(RecyclerView.ViewHolder holder, int position);

    /**
     * HeaderView
     *
     * @param holder
     * @param position
     */
    protected abstract void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int position);

    @Override
    public int getItemCount() {
        int count = getDataCount();
        if (mHeaderView != null) {
            count += 1;
        }
        if (mFooterView != null) {
            count += 1;
        }
        return count;
    }

    public abstract int getDataCount();

    @Override
    public int getItemViewType(int position) {
        if (mHeaderView != null && position == 0) {
            return HEADER_TYPE;
        } else if (mFooterView != null && position == getItemCount() - 1) {
            return FOOTER_TYPE;
        }
        return super.getItemViewType(position);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
