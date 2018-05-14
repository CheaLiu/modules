package com.qi.views.titile;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.qi.R;
import com.qi.utils.TypedArrayParser;


/**
 * Creator LiuQi
 * Date 2018/4/24
 */
public class TitleView extends FrameLayout {

    private FrameLayout mLeftView;
    private ImageView mNavigationView;
    private FrameLayout mMiddleView;
    private TextView mTitleTextView;
    private FrameLayout mRightView;
    private ImageView mFuncView;
    private float mDensity;
    private final static int NORMAL = 0, BOLD = 1, ITALIC = 2;
    private OnClickListener onNavigationViewClickListener;
    private OnClickListener onFuncViewClickListener;

    public TitleView(@NonNull Context context) {
        this(context, null);
    }

    public TitleView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TitleView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
        setLeftView(context);
        setMiddleView(context);
        setRightView(context);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TitleView);
        int navigationPaddingLeft = TypedArrayParser.getDimensionPixelSize(typedArray, R.styleable.TitleView_navigationPaddingLeft, 0);
        int navigationPaddingTop = TypedArrayParser.getDimensionPixelSize(typedArray, R.styleable.TitleView_navigationPaddingTop, 0);
        int navigationPaddingRight = TypedArrayParser.getDimensionPixelSize(typedArray, R.styleable.TitleView_navigationPaddingRight, 0);
        int navigationPaddingBottom = TypedArrayParser.getDimensionPixelSize(typedArray, R.styleable.TitleView_navigationPaddingBottom, 0);
        setNavigationPadding(navigationPaddingLeft, navigationPaddingTop, navigationPaddingRight, navigationPaddingBottom);
        setNavigation(TypedArrayParser.getDrawable(typedArray, R.styleable.TitleView_navigationSrc));

        int titlePaddingLeft = TypedArrayParser.getDimensionPixelSize(typedArray, R.styleable.TitleView_titlePaddingLeft, 0);
        int titlePaddingTop = TypedArrayParser.getDimensionPixelSize(typedArray, R.styleable.TitleView_titlePaddingTop, 0);
        int titlePaddingRight = TypedArrayParser.getDimensionPixelSize(typedArray, R.styleable.TitleView_titlePaddingRight, 0);
        int titlePaddingBottom = TypedArrayParser.getDimensionPixelSize(typedArray, R.styleable.TitleView_titlePaddingBottom, 0);
        setTitlePadding(titlePaddingLeft, titlePaddingTop, titlePaddingRight, titlePaddingBottom);
        setTitle(TypedArrayParser.getString(typedArray, R.styleable.TitleView_titleText));
        setTitleSize(TypedArrayParser.getDimension(typedArray, R.styleable.TitleView_titleSize, 15 * getResources().getDisplayMetrics().density) / mDensity);
        setTitleColor(TypedArrayParser.getColor(typedArray, R.styleable.TitleView_titleColor, Color.BLACK));
        setTypefaceFromAttrs(TypedArrayParser.getInt(typedArray, R.styleable.TitleView_titleStyle, NORMAL));
        int funcPaddingLeft = TypedArrayParser.getDimensionPixelSize(typedArray, R.styleable.TitleView_funcPaddingLeft, 0);
        int funcPaddingTop = TypedArrayParser.getDimensionPixelSize(typedArray, R.styleable.TitleView_funcPaddingTop, 0);
        int funcPaddingRight = TypedArrayParser.getDimensionPixelSize(typedArray, R.styleable.TitleView_funcPaddingLeft, 0);
        int funcPaddingBottom = TypedArrayParser.getDimensionPixelSize(typedArray, R.styleable.TitleView_funcPaddingLeft, 0);
        setFuncPadding(funcPaddingLeft, funcPaddingTop, funcPaddingRight, funcPaddingBottom);
        setFunc(TypedArrayParser.getDrawable(typedArray, R.styleable.TitleView_funcSrc));
        typedArray.recycle();
    }

    private void setTypefaceFromAttrs(int styleIndex) {
        switch (styleIndex) {
            case NORMAL:
                mTitleTextView.setTypeface(Typeface.SANS_SERIF, Typeface.NORMAL);
                break;
            case BOLD:
                mTitleTextView.setTypeface(Typeface.SANS_SERIF, Typeface.BOLD);
                break;
            case ITALIC:
                mTitleTextView.setTypeface(Typeface.SANS_SERIF, Typeface.ITALIC);
                break;
        }
    }

    private void init(Context context) {
        mDensity = getResources().getDisplayMetrics().density;
    }

    public TitleView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }

    private void setLeftView(Context context) {
        mLeftView = new FrameLayout(context);
        mNavigationView = new ImageView(context);
        LayoutParams childLayoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        childLayoutParams.gravity = Gravity.CENTER_VERTICAL;
        mLeftView.addView(mNavigationView, childLayoutParams);
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        addView(mLeftView, layoutParams);
        mNavigationView.setOnClickListener(v -> {
            if (onNavigationViewClickListener != null) onNavigationViewClickListener.onClick(v);
        });
    }

    public void setMiddleView(Context context) {
        mMiddleView = new FrameLayout(context);
        mTitleTextView = new TextView(context);
        LayoutParams childLayoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        childLayoutParams.gravity = Gravity.CENTER;
        mMiddleView.addView(mTitleTextView, childLayoutParams);
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        layoutParams.gravity = Gravity.CENTER;
        addView(mMiddleView, layoutParams);
    }

    public void setRightView(Context context) {
        mRightView = new FrameLayout(context);
        mFuncView = new ImageView(context);
        LayoutParams childLayoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        childLayoutParams.gravity = Gravity.CENTER_VERTICAL;
        mRightView.addView(mFuncView, childLayoutParams);
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        layoutParams.gravity = Gravity.RIGHT;
        addView(mRightView, layoutParams);
        mFuncView.setOnClickListener(v -> {
            if (onFuncViewClickListener != null) onFuncViewClickListener.onClick(v);
        });
    }

    public void setTitlePadding(int left, int top, int right, int bottom) {
        mTitleTextView.setPadding(left, top, right, bottom);
    }

    public void setNavigationPadding(int left, int top, int right, int bottom) {
        mNavigationView.setPadding(left, top, right, bottom);
    }

    public void setFuncPadding(int left, int top, int right, int bottom) {
        mFuncView.setPadding(left, top, right, bottom);
    }

    public void setTitle(String title) {
        mTitleTextView.setText(title);
    }

    public void setTitleColor(int color) {
        mTitleTextView.setTextColor(color);
    }

    public void setTitleSize(float size) {
        mTitleTextView.setTextSize(size);
    }

    public void setNavigation(Drawable drawable) {
        mNavigationView.setImageDrawable(drawable);
    }

    public void setFunc(Drawable drawable) {
        mFuncView.setImageDrawable(drawable);
    }

    /**
     * 设置返回按钮点击事件
     *
     * @param listener
     */
    public void setOnNavigationViewClickListener(OnClickListener listener) {
        this.onNavigationViewClickListener = listener;
    }

    /**
     * 设置功能按钮点击事件
     *
     * @param listener
     */
    public void setOnFuncViewClickListener(OnClickListener listener) {
        this.onFuncViewClickListener = listener;
    }

}
