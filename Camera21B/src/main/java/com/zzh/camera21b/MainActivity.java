package com.zzh.camera21b;

import android.hardware.Camera;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.zzh.camera.LiveCameraView;

/**
 * Created by ZZH on 2017/11/8
 *
 * @date: 2017/11/8 上午10:35
 * @email: zzh_hz@126.com
 * @QQ: 1299234582
 * @author: zzh
 * @Description: 相机21版本之前的使用方式
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    protected void initView() {
        int cameras = Camera.getNumberOfCameras();
        /**
         * Camera 相机是一种硬件资源。
         */
        if (cameras > 0) {
            LiveCameraView cameraView = new LiveCameraView(this, null);
            setContentView(cameraView);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
