package com.qi;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;

import static com.qi.RefreshRecyclerView.RefreshState.FINISH;
import static com.qi.RefreshRecyclerView.RefreshState.REFRESHING;

/**
 * Creator  liuqi
 * Data     2018/4/28
 * Class    com.qi.RefreshRecyclerView
 */
public class RefreshRecyclerView extends RecyclerView {
    private float mDy = 0;
    private float mDistance;
    private View mHeaderView;
    private View mFooterView;
    private int mRefreshState = FINISH;
    private OnRefreshListener onRefreshListener;


    public RefreshRecyclerView(Context context) {
        super(context);
    }

    public RefreshRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public RefreshRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setHeader(View HeaderView) {
        mHeaderView = HeaderView;
    }

    public void setFooter(View footerView) {
        mFooterView = footerView;
        Adapter adapter = getAdapter();
        if (adapter != null) {
            RefreshAdapter refreshAdapter = (RefreshAdapter) adapter;
            refreshAdapter.setFooter(footerView);
        }
    }

    public void finishRefresh() {
        onRefreshListener.onFinish();
        mRefreshState = FINISH;
        Adapter adapter = getAdapter();
        if (adapter != null) {
            RefreshAdapter refreshAdapter = (RefreshAdapter) adapter;
            if (indexOfChild(mHeaderView) != -1) {
                detachViewFromParent(mHeaderView);
            }
            refreshAdapter.removeHeader();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        RefreshAdapter adapter = (RefreshAdapter) getAdapter();
        LinearLayoutManager linearLayoutManager = (LinearLayoutManager) getLayoutManager();
        if (linearLayoutManager.getOrientation() == LinearLayoutManager.VERTICAL) {
            verticalTouchEvent(e, adapter, linearLayoutManager);
        }
        return super.onTouchEvent(e);
    }

    @Override
    public void onScrolled(int dx, int dy) {
        super.onScrolled(dx, dy);
        LinearLayoutManager linearLayoutManager = (LinearLayoutManager) getLayoutManager();
        RefreshAdapter adapter = (RefreshAdapter) getAdapter();
        int lcvp = linearLayoutManager.findLastCompletelyVisibleItemPosition();
        if (dy > 0 && lcvp >= adapter.getItemCount() - 2 && mRefreshState == FINISH) {
            //手势向上滑动，子条目数大于屏幕可显示的条目数，显示的是最后一条数据（倒数第二个条目）,刷新已经完成
            mRefreshState = REFRESHING;
            if (onRefreshListener != null) {
                onRefreshListener.onRefresh();
            }
        }
    }

    private boolean verticalTouchEvent(MotionEvent e, RefreshAdapter adapter, LinearLayoutManager linearLayoutManager) {
        int scaledTouchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
        int fvp = linearLayoutManager.findFirstVisibleItemPosition();
        switch (e.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mDy = e.getY();
                return true;
            case MotionEvent.ACTION_MOVE:
                float my = e.getY();
                mDistance = my - mDy;
                if (mDistance > scaledTouchSlop && fvp == 0 && mRefreshState == FINISH) {
                    mRefreshState = REFRESHING;
                    adapter.addHeader(mHeaderView);
                    if (onRefreshListener != null) {
                        onRefreshListener.onRefresh();
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                mDy = 0;
                break;
        }
        return super.onTouchEvent(e);
    }

    public void setOnRefreshListener(OnRefreshListener onRefreshListener) {
        this.onRefreshListener = onRefreshListener;
    }

    /**
     * 刷新状态
     */
    public interface RefreshState {
        int REFRESHING = 1;
        int FINISH = 2;
    }

    /**
     * 正在刷新接口
     */
    interface OnRefreshListener {
        void onRefresh();

        void onFinish();
    }
}
