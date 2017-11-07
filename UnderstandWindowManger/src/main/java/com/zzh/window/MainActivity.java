package com.zzh.window;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.zzh.window.service.DemoIntentService;

/**
 * Created by ZZH on 2017/10/31
 *
 * @date: 2017/10/31 下午1:50
 * @email: zzh_hz@126.com
 * @QQ: 1299234582
 * @author: zzh
 * @Description: IntentService
 */
public class MainActivity extends AppCompatActivity {
    /**
     * TAG
     */
    public static final String TAG = "sString";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //startActivity(new Intent(this, RemoteActivity.class));
        startService(new Intent(this, DemoIntentService.class).putExtra(DemoIntentService.ACTION, "TASK 1"));
        startService(new Intent(this, DemoIntentService.class).putExtra(DemoIntentService.ACTION, "TASK 2"));
        startService(new Intent(this, DemoIntentService.class).putExtra(DemoIntentService.ACTION, "TASK 3"));
        startService(new Intent(this, DemoIntentService.class).putExtra(DemoIntentService.ACTION, "TASK 4"));
    }
}
