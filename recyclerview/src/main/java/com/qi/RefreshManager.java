package com.qi;

import android.support.v7.widget.RecyclerView;
import android.util.Log;

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
    private OnLoadListener onLoadListener;

    private RefreshManager(RecyclerView recyclerView, OnLoadListener onLoadListener) {
        this.recyclerView = recyclerView;
        this.onLoadListener = onLoadListener;
        recyclerView.addOnScrollListener(this);
    }

    public static RefreshManager newInstance(Object o, OnLoadListener onLoadListener) throws IllegalAccessException {
        Field[] fields = o.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Refresh.class) && field.getType() == RecyclerView.class) {
                return new RefreshManager((RecyclerView) field.get(o), onLoadListener);
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
            onLoadListener.load();
        }
    }

    public interface OnLoadListener {
        void load();
    }
}
