package com.zzh.event.dispatch;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

/**
 * Created by user on 2017/10/17.
 *
 * @date: 2017/10/17
 * @email: zzh_hz@126.com
 * @QQ: 1299234582
 * @author: zzh
 * @description: 事件分发机制
 */
public class TouchViewGroup extends RelativeLayout {
    public static final String TAG = "---group---";

    public TouchViewGroup(Context context) {
        super(context);
    }

    public TouchViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TouchViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.d(TAG, "\t\t\t\tdispatchTouchEvent:" );
        boolean touchEvent = super.dispatchTouchEvent(ev);
        return touchEvent;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.d(TAG, "\t\t\t\tonInterceptTouchEvent: \t");
        boolean touchEvent = super.onInterceptTouchEvent(ev);
        //if (ev.getAction() == MotionEvent.ACTION_DOWN)
        return touchEvent;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(TAG, "\t\t\t\tonTouchEvent: ");
        boolean touchEvent = super.onTouchEvent(event);
        //if (event.getAction() == MotionEvent.ACTION_DOWN)
        return touchEvent;
    }
}
