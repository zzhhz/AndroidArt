package com.zzh.event;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;

/**
 * Created by ZZH on 2017/10/16
 *
 * @date: 2017/10/16 下午3:26
 * @email: zzh_hz@126.com
 * @QQ: 1299234582
 * @author: zzh
 * @Description: View的位置主要由它的四个顶点来决定的。
 * <p>
 * 分别对应于view的四个属性：
 * 1. top 左上角纵坐标；
 * 2. left 左上角横坐标；
 * 3. right 右下角横坐标；
 * 4. bottom 右下角纵坐标
 */
public class MainActivity extends Activity {

    private static final String TAG = "---Activity---";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Window window = getWindow();
        Log.d(TAG, "Window: " + window);
        View decorView = window.getDecorView();
        Log.d(TAG, "decorView: " + decorView);
        View view = decorView.findViewById(android.R.id.content);
        Log.d(TAG, "view: " + view);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(TAG, "\t\t\t\tonTouchEvent: ");
        boolean touchEvent = super.onTouchEvent(event);
        return touchEvent;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.d(TAG, "\t\t\t\tdispatchTouchEvent:");
        boolean touchEvent = super.dispatchTouchEvent(ev);
        return touchEvent;
    }
}