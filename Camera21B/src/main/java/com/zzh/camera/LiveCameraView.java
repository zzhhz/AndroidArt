package com.zzh.camera;

import android.content.Context;
import android.content.res.Configuration;
import android.hardware.Camera;
import android.util.AttributeSet;
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
        final Camera.Parameters params = mCamera.getParameters();
        params.setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);
        params.setSceneMode(Camera.Parameters.SCENE_MODE_BARCODE);
       // mCamera.setParameters(params);
    }



    public LiveCameraView(Context context, Camera camera) {
        super(context);
        mCamera = camera;
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
        mCamera = null;
    }


    /**
     * 检查相机
     */
    private void checkCamera() {
        if (mCamera == null) {
            mCamera = Camera.open(0);
            setCamera(mCamera);
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
