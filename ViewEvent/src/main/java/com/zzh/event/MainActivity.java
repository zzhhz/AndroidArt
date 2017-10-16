package com.zzh.event;

import android.support.v4.widget.ViewDragHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;

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
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "---事件体系---";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewConfiguration vc = ViewConfiguration.get(this);
        int slop = vc.getScaledTouchSlop();
        Log.d(TAG, "---onCreate: " + slop);
        Log.d(TAG, "---onCreate  getApplicationContext: " + ViewConfiguration.get(getApplicationContext()).getScaledTouchSlop());
        Log.d(TAG, "---onCreate  getBaseContext: " + ViewConfiguration.get(getBaseContext()).getScaledTouchSlop());
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        VelocityTracker vt = VelocityTracker.obtain();
        vt.addMovement(event);
        vt.computeCurrentVelocity(500);


        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG, "ACTION_DOWN  X: " + vt.getXVelocity());
                Log.d(TAG, "ACTION_DOWN Y: " + vt.getYVelocity());
                break;
            case MotionEvent.ACTION_UP:
                Log.d(TAG, "ACTION_UP X: " + vt.getXVelocity());
                Log.d(TAG, "ACTION_UP Y: " + vt.getYVelocity());
                break;
        }
        vt.clear();
        vt.recycle();
        return super.onTouchEvent(event);
    }
}
