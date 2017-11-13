package com.zzh.camera;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.ImageFormat;
import android.hardware.Camera;
import android.os.Build;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;

/**
 * Created by user on 2017/11/8.
 *
 * @date: 2017/11/8
 * @email: zzh_hz@126.com
 * @QQ: 1299234582
 * @author: zzh
 * @description:
 */
public class LiveCameraView extends SurfaceView implements SurfaceHolder.Callback {
    private final static String TAG = LiveCameraView.class.getSimpleName();
    private Camera mCamera;
    private SurfaceHolder mSurfaceHolder;
    private Context mContext;


    public void setCamera(Camera camera) {
        //

        /*
        if (Build.MODEL.equals("KORIDY H30")){
            params.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);

        } else {
            params.setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);
        }
        mCamera.setParameters(params);*/

        mCamera.autoFocus(new Camera.AutoFocusCallback() {
            @Override
            public void onAutoFocus(boolean success, Camera camera) {
                if (success) {
                    camera.cancelAutoFocus();// 只有加上了这一句，才会自动对焦。
                    Camera.Parameters params = mCamera.getParameters();
                    params.setPictureFormat(ImageFormat.JPEG);
                    params.setFlashMode(Camera.Parameters.FLASH_MODE_AUTO);
                    if (!Build.MODEL.equals("KORIDY H30")) {
                        params.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);// 1连续对焦
                    }else{
                        params.setFlashMode(Camera.Parameters.FLASH_MODE_AUTO);
                    }
                    camera.setParameters(params);
                }
            }
        });
    }


    public LiveCameraView(Context context, Camera camera) {
        super(context);
        mCamera = camera;
        if (mCamera == null) {
            mCamera = Camera.open(0);
        }
        setCamera(camera);
        mContext = context;
        mSurfaceHolder = getHolder();
        mSurfaceHolder.addCallback(this);
    }

    /**
     * 开始预览。
     */
    private void startPreviewDisplay() {
        checkCamera();
        try {
            mCamera.setPreviewDisplay(mSurfaceHolder);
            mCamera.startPreview();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 结束预览
     */
    private void stopPreviewDisplay() {
        checkCamera();
        mSurfaceHolder.removeCallback(this);
        mCamera.setPreviewCallback(null);
        mCamera.stopPreview();
        mCamera.lock();
        mCamera.release();
        mCamera = null;
    }


    /**
     * 检查相机
     */
    private void checkCamera() {
        if (mCamera == null) {
            mCamera = Camera.open(0);
            setCamera(mCamera);
            followScreenOrientation(mContext, mCamera);
        }
    }

    /**
     * surface view 创建是调用此方法
     *
     * @param holder
     * @from interface SurfaceHolder.Callback
     */
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        startPreviewDisplay();
    }

    /**
     * @param holder
     * @from interface SurfaceHolder.Callback
     */
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        if (mSurfaceHolder.getSurface() == null) {
            return;
        }
        followScreenOrientation(mContext, mCamera);
        stopPreviewDisplay();
        startPreviewDisplay();
    }

    public static void followScreenOrientation(Context context, Camera camera) {
        final int orientation = context.getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            camera.setDisplayOrientation(180);
        } else if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            camera.setDisplayOrientation(90);
        }
    }

    /**
     * surface view 销毁
     *
     * @param holder
     * @from interface SurfaceHolder.Callback
     */
    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        stopPreviewDisplay();

    }
}
