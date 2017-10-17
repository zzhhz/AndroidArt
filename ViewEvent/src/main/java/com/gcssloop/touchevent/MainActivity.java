package com.gcssloop.touchevent;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;

import com.gcssloop.touchevent.test.Static;
import com.zzh.event.R;

public class MainActivity extends Activity {
    private static final String TAG = Static.TAG1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN){
            Log.i(TAG, Static.dispatchTouchEvent+"现在项目做到什么程度了?");
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN){
            //Log.i(TAG, Static.onTouchEvent+"这么简单都做不了,你们都是干啥的(愤怒).");
            Log.i(TAG, Static.onTouchEvent);
        }
        return super.onTouchEvent(event);
    }

}
