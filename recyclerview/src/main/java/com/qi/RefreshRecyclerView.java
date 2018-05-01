package com.qi;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.view.animation.LinearInterpolator;

/**
 * Creator  liuqi
 * Data     2018/4/28
 * Class    com.qi.RefreshRecyclerView
 */
public class RefreshRecyclerView extends RecyclerView {

    private float mDownY = 0;
    private float mDistance;
    private String mTtext;
    private Bitmap[] mBitmaps;
    private Bitmap currentBitmap;
    ValueAnimator valueAnimator;
    private TextPaint mTextPaint;
    private Rect mTextBound;

    public RefreshRecyclerView(Context context) {
        super(context);
    }

    public RefreshRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public RefreshRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    void setHeadText(String text) {
        mTtext = text;
        mTextPaint = new TextPaint();
        mTextBound = new Rect();
        mTextPaint.getTextBounds(text, 0, text.length(), mTextBound);
    }

    void setHeadBitmap(final Bitmap[] bitmaps) {
        mBitmaps = bitmaps;
        if (bitmaps != null && bitmaps.length > 1) {
            valueAnimator = ValueAnimator.ofInt(0, bitmaps.length);
            valueAnimator.setDuration(300);
            valueAnimator.setInterpolator(new LinearInterpolator());
            valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
            valueAnimator.setRepeatMode(ValueAnimator.RESTART);
            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    Integer value = (Integer) animation.getAnimatedValue();
                    if (value == bitmaps.length) {
                        return;
                    }
                    currentBitmap = mBitmaps[value];
                    invalidate(0, 0, getMeasuredWidth(), (int) mDistance);
                }
            });
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent e) {
        switch (e.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mDownY = e.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                float my = e.getY();
                mDistance = my - mDownY;
                if (computeVerticalScrollOffset() == 0 && mDistance > ViewConfiguration.get(getContext()).getScaledTouchSlop()) {
                    setPadding(0, (int) mDistance, 0, 0);
                    if (!valueAnimator.isStarted()) {
                        valueAnimator.start();
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                valueAnimator.cancel();
                currentBitmap = null;
                setPadding(0, 0, 0, 0);
                invalidate(0, 0, getMeasuredWidth(), (int) mDistance);
                mDownY = 0;
                mDistance = 0;
                break;
        }
        return super.onTouchEvent(e);
    }

    @Override
    public void draw(Canvas c) {
        super.draw(c);
        String text = "";
        if (!TextUtils.isEmpty(mTtext)) {
            if (mDistance > 0) {
                text = mTtext;
            } else if (mDistance == 0) {
                text = "";
            }
            c.drawText(text, (getMeasuredWidth() - mTextBound.width()) / 2, (mDistance - mTextBound.height()) / 2, mTextPaint);
        }
        if (currentBitmap != null) {
            Paint paint = new Paint();
            c.drawBitmap(currentBitmap, (getMeasuredWidth() - currentBitmap.getWidth()) / 2, (mDistance - currentBitmap.getHeight()) / 2, paint);
        }
    }
}
