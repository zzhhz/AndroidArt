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
public class TouchActivityGroup extends RelativeLayout {
    public static final String TAG = "---ActivityGroup---";

    public TouchActivityGroup(Context context) {
        super(context);
    }

    public TouchActivityGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TouchActivityGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.d(TAG, "\t\t\t\tdispatchTouchEvent:");
        boolean touchEvent = super.dispatchTouchEvent(ev);
        return touchEvent;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.d(TAG, "\t\t\t\tonInterceptTouchEvent:");
        boolean touchEvent = super.onInterceptTouchEvent(ev);
        return touchEvent;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(TAG, "\t\t\t\tonTouchEvent:");
        boolean touchEvent = super.onTouchEvent(event);
        return touchEvent;
    }
}
