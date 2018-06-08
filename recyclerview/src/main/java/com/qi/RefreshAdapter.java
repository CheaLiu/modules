package com.qi;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Creator  liuqi
 * Data     2018/4/24
 * Class    com.qi.RefreshAdapter
 */
public abstract class RefreshAdapter<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    private View mHeaderView;
    private View mFooterView;
    private static final int HEADER_TYPE = 1;
    private static final int FOOTER_TYPE = 2;

    protected void setHeader(View headerView) {
        if (mHeaderView != null) {
            notifyItemRemoved(0);
        }
        mHeaderView = headerView;
        notifyItemInserted(0);
    }

    protected void addHeader(View headerView) {
        if (headerView == null) return;
        mHeaderView = headerView;
        notifyItemInserted(0);
    }

    protected void removeHeader() {
        if (mHeaderView != null) {
            notifyItemRemoved(0);
            mHeaderView = null;
        }
    }

    protected void setFooter(View footerView) {
        if (mFooterView != null) {
            notifyItemRemoved(getItemCount() - 1);
        }
        mFooterView = footerView;
        notifyItemInserted(getItemCount() - 1);
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == HEADER_TYPE)
            return (VH) new ViewHolder(mHeaderView);
        else if (viewType == FOOTER_TYPE)
            return (VH) new ViewHolder(mFooterView);
        return onNormalCreateViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
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

    public abstract VH onNormalCreateViewHolder(ViewGroup parent);

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
