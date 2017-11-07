package com.zzh.window;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.HandlerThread;
import android.util.Log;

/**
 * Created by user on 2017/11/6.
 *
 * @date: 2017/11/6
 * @email: zzh_hz@126.com
 * @QQ: 1299234582
 * @author: zzh
 * @description: 接收广播，不在同一个进程
 */
public class BroadCastRemoteReceiver extends BroadcastReceiver {
    private String TAG = "BroadCastRemoteReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "onReceive: " + intent.getAction());
    }
}
