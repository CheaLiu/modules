package com.qi;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.lang.reflect.Field;

import static android.support.v7.widget.RecyclerView.SCROLL_STATE_DRAGGING;
import static android.support.v7.widget.RecyclerView.SCROLL_STATE_IDLE;
import static android.support.v7.widget.RecyclerView.SCROLL_STATE_SETTLING;

/**
 * Creator  liuqi
 * Data     2018/4/24
 * Class    com.qi.RefreshManager
 */
public class RefreshManager extends RecyclerView.OnScrollListener {
    private boolean isLoad = true;
    RecyclerView recyclerView;
    private View header;
    private View footer;
    private OnLoadListener onLoadListener;

    public RefreshManager(RecyclerView recyclerView, View header, View footer, OnLoadListener onLoadListener) {
        this.recyclerView = recyclerView;
        this.header = header;
        this.footer = footer;
        this.onLoadListener = onLoadListener;
        recyclerView.addOnScrollListener(this);
    }

    public static RefreshManager newInstance(Object o, View header, View footer, OnLoadListener onLoadListener) throws IllegalAccessException {
        Field[] fields = o.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Refresh.class) && field.getType() == RecyclerView.class) {
                return new RefreshManager((RecyclerView) field.get(o), header, footer, onLoadListener);
            }
        }
        return null;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        Log.d("refresh_tag", dx + "----" + dy);
        if (dy > 0 && isLoad) {
            isLoad = false;
            load();
        }
    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        switch (newState) {
            case SCROLL_STATE_IDLE:
                //滑动停止后恢复加载
                isLoad = true;
                break;
            case SCROLL_STATE_DRAGGING:
                break;
            case SCROLL_STATE_SETTLING:
                break;
        }
    }

    private void load() {
        if (onLoadListener != null) {
            RecyclerView.Adapter adapter = recyclerView.getAdapter();
            if (adapter instanceof RefreshAdapter) {
                ((RefreshAdapter) adapter).setFooter(footer);
            }
            onLoadListener.load();
        }
    }

    public interface OnLoadListener {
        void load();
    }

    public static class Builder {
        private View footer;
        private View header;
        private OnLoadListener onLoadListener;
        private RecyclerView recyclerView;

        public Builder recyclerView(RecyclerView recyclerView) {
            this.recyclerView = recyclerView;
            return this;
        }

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
            return new RefreshManager(recyclerView, header, footer, onLoadListener);
        }
    }
}
