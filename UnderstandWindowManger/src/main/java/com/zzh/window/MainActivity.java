package com.zzh.window;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

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
    public static final String sString = "sString";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(this, sString, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent();
        ComponentName service = startService(intent);



    }
}
