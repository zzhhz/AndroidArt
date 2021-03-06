package com.zzh.event.dispatch;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by user on 2017/10/17.
 *
 * @date: 2017/10/17
 * @email: zzh_hz@126.com
 * @QQ: 1299234582
 * @author: zzh
 * @description:
 */
public class TouchView extends View {
    public static final String TAG = "---view---";

    public TouchView(Context context) {
        super(context);
    }

    public TouchView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TouchView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.d(TAG, "\t\t\t\tdispatchTouchEvent: ");
        boolean touchEvent = super.dispatchTouchEvent(ev);
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
