package com.zzh.window;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by user on 2017/11/6.
 *
 * @date: 2017/11/6
 * @email: zzh_hz@126.com
 * @QQ: 1299234582
 * @author: zzh
 * @description: 广播
 */
public class RemoteActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remote);
    }

    /**
     * @param v
     */
    public void onClickView(View v) {
        switch (v.getId()) {
            case R.id.sendBroadCast:
                sendBroadCast();
                break;
            case R.id.sendRemote:
                sendRemoteBroadCast();
                break;
        }

    }

    private void sendBroadCast() {
        Intent intent = new Intent();
        intent.setAction(Constants.ACTION_COMMON_CAST);
        sendBroadcast(intent);
    }

    private void sendRemoteBroadCast() {
        Intent intent = new Intent();
        intent.setAction(Constants.ACTION_REMOTE_CAST);
        sendBroadcast(intent);
    }
}
