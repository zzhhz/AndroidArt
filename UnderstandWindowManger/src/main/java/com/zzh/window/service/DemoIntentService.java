package com.zzh.window.service;

import android.app.IntentService;
import android.content.Intent;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by user on 2017/11/7.
 *
 * @date: 2017/11/7
 * @email: zzh_hz@126.com
 * @QQ: 1299234582
 * @author: zzh
 * @description: 是按照顺序启动的，处理的任务也是按照启动顺序来处理的。
 */
public class DemoIntentService extends IntentService {
    public static final String ACTION = "DemoIntentService";

    public DemoIntentService() {
        super(ACTION);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        String action = intent.getStringExtra(ACTION);
        Log.d(ACTION, "onHandleIntent: " + action);
        SystemClock.sleep(3000);

    }

    @Override
    public void onDestroy() {
        Log.d(ACTION, "onDestroy: 寿终正寝");
        super.onDestroy();
    }
}
