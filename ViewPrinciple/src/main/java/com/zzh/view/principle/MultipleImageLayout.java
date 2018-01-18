package com.zzh.view.principle;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2018/1/18.
 *
 * @date: 2018/1/18
 * @email: zzh_hz@126.com
 * @QQ: 1299234582
 * @author: zzh
 * @description:
 */
public class MultipleImageLayout extends ViewGroup {

    public static final String TAG = "MultipleImageLayout";


    /**
     * 显示列数
     */
    private int mMaxColumn = 4;
    /**
     * 最多显示几张
     */
    private int mMaxShow = 4;
    private int mUsefulWidth;
    private List<ImageView> mChildView = new ArrayList<>();
    private int mImageSize = -1;
    private boolean flagCaculateImage = false;
    private int mLineHeiht = 0;
    private int mLineWidth = 0;


    public MultipleImageLayout(Context context) {
        super(context, null);
    }

    public MultipleImageLayout(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
    }

    public MultipleImageLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (attrs != null) {
            init(context, attrs);
        }
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.MultipleImageLayout);
        mMaxColumn = array.getInt(R.styleable.MultipleImageLayout_image_column, mMaxColumn);
        if (mMaxColumn < 2) {
            mMaxColumn = 2;
        }
        mMaxShow = array.getInt(R.styleable.MultipleImageLayout_image_max_show, mMaxShow);
        mLineHeiht = array.getDimensionPixelOffset(R.styleable.MultipleImageLayout_image_row_dimen, 3);
        mLineWidth = array.getDimensionPixelOffset(R.styleable.MultipleImageLayout_image_column_dimen, 3);
        array.recycle();

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            int lines = i / (mMaxColumn);
            int lc = i % mMaxColumn * mImageSize + getPaddingLeft() + ((MarginLayoutParams) childAt.getLayoutParams()).leftMargin;
            int tc = lines * mImageSize + ((MarginLayoutParams) childAt.getLayoutParams()).topMargin;
            int rc = i % mMaxColumn * mImageSize + childAt.getMeasuredWidth() - ((MarginLayoutParams) childAt.getLayoutParams()).rightMargin;
            int bc = lines * mImageSize + childAt.getMeasuredHeight() - ((MarginLayoutParams) childAt.getLayoutParams()).bottomMargin;
            childAt.layout(lc, tc, rc, bc);
            Log.d(TAG, "onLayout: line: " + lines + ", position: " + i + ", lc : " + lc);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int mPaddingLeft = getPaddingLeft();
        int mPaddingRight = getPaddingRight();
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        Log.d(TAG, "onMeasure: widthSize: " + widthSize + ", widthMode: " + widthMode + ", heightSize: " + heightSize + ", heightMode: " + heightMode);
        mUsefulWidth = widthSize - mPaddingLeft - mPaddingRight;
        if (!flagCaculateImage) {
            flagCaculateImage = !flagCaculateImage;
            mImageSize = mUsefulWidth / mMaxColumn;
        }
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (childAt.getVisibility() == GONE) {
                continue;
            }
            measureChild(childAt, widthMeasureSpec, heightMeasureSpec);
            MarginLayoutParams params = (MarginLayoutParams) childAt.getLayoutParams();

            params.height = mImageSize;
            params.width = mImageSize;

            setMeasuredDimension((widthMode == MeasureSpec.EXACTLY ? widthSize : mImageSize), (heightMode == MeasureSpec.EXACTLY ? heightSize : mImageSize));
        }
        Log.d(TAG, "onMeasure: childCount: " + childCount + ", getChildAt: " + getChildAt(0));
    }

    @Override
    protected LayoutParams generateLayoutParams(LayoutParams p) {
        return new MarginLayoutParams(p);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new MarginLayoutParams(super.generateDefaultLayoutParams());
    }
}
