package com.zzh.camera;

import android.content.Context;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by user on 2017/11/10.
 *
 * @date: 2017/11/10
 * @email: zzh_hz@126.com
 * @QQ: 1299234582
 * @author: zzh
 * @description:
 */
public class LiveCamera21AView extends SurfaceView implements SurfaceHolder.Callback{


    public LiveCamera21AView(Context context) {
        super(context);
    }

    public LiveCamera21AView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void openCamera21a() {

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }
}
