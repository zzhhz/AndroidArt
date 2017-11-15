package com.zzh.ipc;

import android.app.Activity;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by ZZH on 2017/11/15
 *
 * @date: 2017/11/15 下午2:57
 * @email: zzh_hz@126.com
 * @QQ: 1299234582
 * @author: zzh
 * @Description: handler 更新UI
 */
public class MainActivity extends AppCompatActivity {

    private Handler cHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        HandlerThread thread = new HandlerThread("thread 2");
        thread.start();
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("-----", "onClick: ");
                onClickView(v);
            }
        });

        mHandler = new Handler(thread.getLooper()) {
            @Override
            public void handleMessage(Message msg) {
                Toast.makeText(MainActivity.this, msg.obj.toString() + " / " + Thread.currentThread().getName(), Toast.LENGTH_SHORT).show();
            }
        };

    }

    public Handler mHandler = new Handler();

    public int count = 0;

    /**
     * @param v
     */
    public void onClickView(View v) {
        if (v instanceof Button) {
            /*final Button btn = (Button) v;
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    btn.setText(SystemClock.uptimeMillis() + "/");
                }
            });*/
            Message msg = Message.obtain();
            if (mHandler != null) {
                count++;
                msg.obj = "  点击 " + count;
                Log.d("------", "onClickView: ");
                mHandler.sendMessage(msg);
            } else {
                Log.d("------", "onClickView: cHandler为空");
            }

        }

    }

    class TestRunnable implements Runnable {
        Call call;
        Looper mLooper;

        public TestRunnable(Call call) {
            this.call = call;
            Looper.prepare();
            mLooper = Looper.myLooper();
        }

        @Override
        public void run() {
            Looper.loop();
        }
    }

    interface Call {
        void call(Handler child);
    }
}
