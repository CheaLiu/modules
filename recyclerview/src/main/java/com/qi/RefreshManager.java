package com.qi;

import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;

/**
 * Creator  liuqi
 * Data     2018/4/24
 * Class    com.qi.RefreshManager
 */
public class RefreshManager extends RecyclerView.OnScrollListener implements View.OnClickListener {
    /**
     * 列表是否是可加载状态
     */
    private boolean isLoading = true;
    private OnLoadListener onLoadListener;
    /**
     * 是否自动刷新
     */
    private boolean mAuto;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager mLayoutManager;
    private RefreshManager(RecyclerView recyclerView, View headerView, View footerView, OnLoadListener onLoadListener, boolean auto) {
        this.recyclerView = recyclerView;
        this.adapter = recyclerView.getAdapter();
        this.onLoadListener = onLoadListener;
        this.mAuto = auto;
        this.mLayoutManager = recyclerView.getLayoutManager();
        if (adapter instanceof RefreshAdapter) {
            ((RefreshAdapter) adapter).setFooter(footerView);
        }
        recyclerView.addOnScrollListener(this);
        footerView.setOnClickListener(this);
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        if (!mAuto) return;
        if (dy == 0) return;
        load();
    }

    /**
     * 加载数据时，列表不可加载,isloadable设置false
     */
    private void load() {
        if (isLoading) {
            if (onLoadListener != null) {
                onLoadListener.onLoad();
            }
        }
        isLoading = false;
    }

    /**
     * 加载成功，刷新数据
     */
    public void loadSuccess() {
        //滑动停止后恢复加载
        isLoading = true;
        adapter.notifyDataSetChanged();
    }

    /**
     * 加载成功，刷新数据
     */
    public void loadSuccess(int positionStart, int itemCount, Object payload) {
        //滑动停止后恢复加载
        isLoading = true;
        adapter.notifyItemRangeChanged(positionStart, itemCount, payload);
    }

    /**
     * 加载失败
     */
    public void loadFail() {
        isLoading = true;
    }

    @Override
    public void onClick(View v) {
        load();
    }


    public interface OnLoadListener {
        void onLoad();
    }

    public static class Builder {
        private View footer;
        private View header;
        private OnLoadListener onLoadListener;
        private RecyclerView recyclerView;
        private boolean mAuto;

        public Builder recyclerView(RecyclerView recyclerView) {
            this.recyclerView = recyclerView;
            return this;
        }

        /**
         * 添加加载View
         *
         * @param footer
         * @return
         */
        public Builder footer(View footer) {
            this.footer = footer;
            return this;
        }

        public Builder header(View header) {
            this.header = header;
            return this;
        }

        public Builder loadListener(OnLoadListener onLoadListener) {
            this.onLoadListener = onLoadListener;
            return this;
        }

        public RefreshManager builder() {
            return new RefreshManager(recyclerView, header, footer, onLoadListener, mAuto);
        }

        public Builder auto(boolean auto) {
            mAuto = auto;
            return this;
        }
    }
}
