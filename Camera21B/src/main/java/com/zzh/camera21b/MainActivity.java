package com.zzh.camera21b;

import android.Manifest;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.hardware.Camera;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.zzh.camera.LiveCameraView;
import com.zzh.utils.Utils;
import com.zzh.zlibs.base.BaseNoSwipeBackActivity;

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
            /*Camera.Parameters parameters = open.getParameters();
            //配置光线不足自动闪光
            parameters.setFlashMode(Camera.Parameters.FLASH_MODE_AUTO);
            //自动聚焦
            parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);
            int orientation = getResources().getConfiguration().orientation;
            if(orientation == Configuration.ORIENTATION_LANDSCAPE) {
                open.setDisplayOrientation(180);
            }else if(orientation == Configuration.ORIENTATION_PORTRAIT) {
                open.setDisplayOrientation(90);
            }*/
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
