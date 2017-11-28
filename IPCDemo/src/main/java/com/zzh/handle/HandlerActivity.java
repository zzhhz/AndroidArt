package com.zzh.handle;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.zzh.ipc.R;

/**
 * Created by user on 2017/11/28.
 *
 * @date: 2017/11/28
 * @email: zzh_hz@126.com
 * @QQ: 1299234582
 * @author: zzh
 * @description: Handler MessageQueue Looper
 */
public class HandlerActivity extends AppCompatActivity {
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Log.d("child", "----" + Thread.currentThread().getName());
        }
    };

    Handler childHandler = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);
        Thread child = new Thread("child thread") {
            @Override
            public void run() {
                //Looper.prepare();
                childHandler = new Handler() {
                    @Override
                    public void handleMessage(Message msg) {
                        Log.d("child", "----" + Thread.currentThread().getName() + ",  " + msg.obj);
                    }
                };
                //Looper.loop();
            }
        };
        child.start();
    }

    public void onClickView(View v) {
        switch (v.getId()) {
            case R.id.button: {
                Thread thread = new Thread() {
                    @Override
                    public void run() {
                        Message msg = Message.obtain();
                        handler.sendMessage(msg);
                    }
                };
                thread.start();
                break;
            }
            case R.id.button1: {
                if (childHandler != null) {
                    Message msg = Message.obtain();
                    msg.obj = "123---" + SystemClock.uptimeMillis();
                    childHandler.sendMessage(msg);

                } else {
                    Log.d("-----", "onClickView: child Handler is null");
                }

                break;
            }
            default:
                break;
        }
    }
}
