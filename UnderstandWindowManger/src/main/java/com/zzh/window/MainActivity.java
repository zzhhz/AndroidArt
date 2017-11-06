package com.zzh.window;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by ZZH on 2017/10/31
 *
 * @date: 2017/10/31 下午1:50
 * @email: zzh_hz@126.com
 * @QQ: 1299234582
 * @author: zzh
 * @Description:
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
        startActivity(new Intent(this, RemoteActivity.class));

    }
}
