package com.zzh.event;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.annotation.Px;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Scroller;

/**
 * Created by user on 2017/10/16.
 *
 * @date: 2017/10/16
 * @email: zzh_hz@126.com
 * @QQ: 1299234582
 * @author: zzh
 * @description: 弹性view
 */
public class ElasticView extends View {
    private Context mContext;

    public ElasticView(Context context) {
        super(context);
        this.mContext = context;
    }

    public ElasticView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
    }

    public ElasticView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
    }


    @Override
    public void scrollTo(@Px int x, @Px int y) {
        super.scrollTo(x, y);
    }

    Scroller mScroller = new Scroller(mContext);

    private void smoothScrollTo(int destX, int destY) {
        int scrollX = getScrollX();
        int deltaX = destX - scrollX;
        mScroller.startScroll(scrollX, 0, deltaX, 1000);
        invalidate();
    }

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            postInvalidate();
        }
    }
}
