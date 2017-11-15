package com.zzh.camera;

import android.Manifest;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.ImageFormat;
import android.graphics.Rect;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CameraMetadata;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.params.MeteringRectangle;
import android.media.Image;
import android.media.ImageReader;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;

import java.nio.ByteBuffer;
import java.util.Arrays;

/**
 * Created by user on 2017/11/10.
 *
 * @date: 2017/11/10
 * @email: zzh_hz@126.com
 * @QQ: 1299234582
 * @author: zzh
 * @description: 21之后的相机使用
 */
@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class LiveCamera21AView extends SurfaceView implements SurfaceHolder.Callback {
    private SurfaceHolder mSurfaceHolder;
    private CameraDevice mCameraDevice;
    private CameraManager mCameraManager;
    private String mCameraId;
    private ImageReader mImageReader;
    private CameraCaptureSession mCameraCaptureSession;
    private Handler childHandler, mainHandler;
    protected int width, height;
    private Context mContext;
    private OnCameraClickListener mOnCameraClickListener;

    public void setOnCameraClickListener(OnCameraClickListener onCameraClickListener) {
        mOnCameraClickListener = onCameraClickListener;
    }

    public LiveCamera21AView(Context context) {
        this(context, null);
    }

    public LiveCamera21AView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        init();
    }

    /**
     * 初始化的一些信息
     */
    private void init() {
        mSurfaceHolder = getHolder();
        mSurfaceHolder.setKeepScreenOn(true);
        mSurfaceHolder.addCallback(this);
        //初始化屏幕信息
        if (width < 1 || height < 1) {
            int[] widthAndHeight = getDisplayWidthAndHeight();
            width = widthAndHeight[0];
            height = widthAndHeight[1];
        }

    }

    /**
     * 打开相机操作
     */
    public void openCamera21a() {
        Log.d(TAG, "openCamera21a: 初始化相机");
        HandlerThread handlerThread = new HandlerThread("Camera2");
        handlerThread.start();
        childHandler = new Handler(handlerThread.getLooper());
        mainHandler = new Handler(Looper.getMainLooper());
        mCameraId = String.valueOf(CameraCharacteristics.LENS_FACING_FRONT);
        /**
         * 图片预览的信息
         * 图片宽，高，图片格式，拍出的照片数量
         */
        mImageReader = ImageReader.newInstance(width, height, ImageFormat.JPEG, 1);

        mImageReader.setOnImageAvailableListener(new ImageReader.OnImageAvailableListener() {
            @Override
            public void onImageAvailable(ImageReader reader) {
                //进行拍照或得到image的处理。
                Image image = reader.acquireNextImage();
                ByteBuffer byteBuffer = image.getPlanes()[0].getBuffer();
                //将image字节，放到bytes中
                byte[] bytes = new byte[byteBuffer.remaining()];
                byteBuffer.get(bytes);
                if (mOnCameraClickListener != null) {
                    mOnCameraClickListener.onTakePicture(bytes);
                }
                //转换成bitmap对象。

            }
        }, mainHandler);

        mCameraManager = (CameraManager) mContext.getSystemService(Context.CAMERA_SERVICE);
        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {

            try {
                Log.d(TAG, "openCamera21a: 打开相机");
                mCameraManager.openCamera(mCameraId, mStateCallback, mainHandler);
            } catch (CameraAccessException ex) {
                Log.d("------", "openCamera21a: " + ex.getMessage());
            }
            return;
        } else {
            Log.d(TAG, "openCamera21a: 未获得权限信息");
        }


    }

    private CameraDevice.StateCallback mStateCallback = new CameraDevice.StateCallback() {
        @Override
        public void onOpened(@NonNull CameraDevice camera) {
            //打开的时候，开始预览，并投放到surface view
            mCameraDevice = camera;
            takePreview();
        }

        @Override
        public void onDisconnected(@NonNull CameraDevice camera) {
            if (mCameraDevice != null) {
                mCameraDevice.close();
                mCameraDevice = null;
            }
            Log.d(TAG, "onDisconnected: 断开相机的连接");
        }

        @Override
        public void onError(@NonNull CameraDevice camera, int error) {
            if (camera != null) {
                camera.close();
                camera = null;
                mCameraDevice = null;
            }
            Toast.makeText(mContext, "相机打开失败:  " + error, Toast.LENGTH_SHORT).show();
        }
    };

    /**
     * 实时预览图片
     */
    private void takePreview() {
        try {
            final CaptureRequest.Builder builder = mCameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW);


            builder.addTarget(mSurfaceHolder.getSurface());
            mCameraDevice.createCaptureSession(Arrays.asList(mSurfaceHolder.getSurface(), mImageReader.getSurface()), new CameraCaptureSession.StateCallback() {
                @Override
                public void onConfigured(@NonNull CameraCaptureSession session) {
                    if (mCameraDevice == null) {
                        return;
                    }
                    mCameraCaptureSession = session;
                    builder.set(CaptureRequest.CONTROL_AF_TRIGGER, CameraMetadata.CONTROL_AF_TRIGGER_CANCEL);
                    builder.set(CaptureRequest.CONTROL_AF_MODE, CaptureRequest.CONTROL_AF_MODE_CONTINUOUS_PICTURE);
                    builder.set(CaptureRequest.CONTROL_AE_MODE, CaptureRequest.CONTROL_AE_MODE_ON_AUTO_FLASH);
                    /*Rect rect = new Rect();
                    builder.set(CaptureRequest.CONTROL_AF_REGIONS, new MeteringRectangle[]{new MeteringRectangle(rect, 100)});*/
                    try {
                        mCameraCaptureSession.setRepeatingRequest(builder.build(), null, childHandler);
                    } catch (CameraAccessException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onConfigureFailed(@NonNull CameraCaptureSession session) {

                }
            }, childHandler);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        openCamera21a();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    public static final String TAG = "------";

    /**
     * 屏幕的宽、高
     *
     * @return 一维数组
     */
    public int[] getDisplayWidthAndHeight() {
        DisplayMetrics metrics = mContext.getResources().getDisplayMetrics();
        Log.d(TAG, "getDisplayWidthAndHeight: " + metrics.widthPixels + ", " + metrics.heightPixels);
        return new int[]{metrics.widthPixels, metrics.heightPixels};
    }

    /**
     * 将点击事件回调出去
     */
    public interface OnCameraClickListener {
        void onTakePicture(byte[] picture);

    }
}
