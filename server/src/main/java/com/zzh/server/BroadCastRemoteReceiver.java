package com.zzh.server;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by user on 2017/11/6.
 *
 * @date: 2017/11/6
 * @email: zzh_hz@126.com
 * @QQ: 1299234582
 * @author: zzh
 * @description:
 */
public class BroadCastRemoteReceiver extends BroadcastReceiver {
    private String TAG = "Another";
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "onReceive: "+intent.getAction());

    }
}
