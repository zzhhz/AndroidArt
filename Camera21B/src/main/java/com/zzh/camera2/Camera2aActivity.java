package com.zzh.camera2;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.zzh.camera.LiveCamera21AView;
import com.zzh.camera21b.R;

/**
 * Created by user on 2017/11/14.
 *
 * @date: 2017/11/14
 * @email: zzh_hz@126.com
 * @QQ: 1299234582
 * @author: zzh
 * @description:
 */
public class Camera2aActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Log.d("------", "onCreate: 使用的是camera 2");
            setContentView(new LiveCamera21AView(this));
        } else {
            setContentView(R.layout.activity_camera);
        }
    }
}
