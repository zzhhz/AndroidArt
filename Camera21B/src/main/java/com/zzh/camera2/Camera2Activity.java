package com.zzh.camera2;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageFormat;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CaptureRequest;
import android.media.Image;
import android.media.ImageReader;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.util.SparseArray;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.zzh.camera21b.R;
import com.zzh.zlibs.base.BaseActivity;

import java.nio.ByteBuffer;
import java.util.Arrays;

/**
 * Created by user on 2017/11/10.
 *
 * @date: 2017/11/10
 * @email: zzh_hz@126.com
 * @QQ: 1299234582
 * @author: zzh
 * @description:
 */
public class Camera2Activity extends BaseActivity {

    private static final SparseArray ORIENTATIONS = new SparseArray();

    static {
        ORIENTATIONS.append(Surface.ROTATION_0, 90);
        ORIENTATIONS.append(Surface.ROTATION_90, 0);
        ORIENTATIONS.append(Surface.ROTATION_180, 270);
        ORIENTATIONS.append(Surface.ROTATION_270, 180);
    }

    private SurfaceView mSurfaceView;
    private SurfaceHolder mSurfaceHolder;
    private ImageView iv_show;
    private CameraManager mCameraManager;
    private Handler childHandler, mainHandler;
    private String mCameraId;
    private ImageReader mImageReader;
    private CameraCaptureSession mCameraCaptureSession;
    private CameraDevice mCameraDevice;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_camera;
    }

    @Override
    protected void initView() {
        mSurfaceView = (SurfaceView) findViewById(R.id.surfaceView);
        mSurfaceView.setOnClickListener(this);
        mSurfaceHolder = mSurfaceView.getHolder();
        mSurfaceHolder.setKeepScreenOn(true);
        mSurfaceHolder.addCallback(new SurfaceHolder.Callback() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                initCamera2();
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {

            }
        });

    }

    /**
     * 初始化相机
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void initCamera2() {
        HandlerThread handlerThread = new HandlerThread("camera2");
        handlerThread.start();
        childHandler = new Handler(handlerThread.getLooper());
        mainHandler = new Handler(getMainLooper());
        mCameraId = "" + CameraCharacteristics.LENS_FACING_FRONT;
        mImageReader = ImageReader.newInstance(1080, 1920, ImageFormat.JPEG, 1);
        mImageReader.setOnImageAvailableListener(new ImageReader.OnImageAvailableListener() {

            @Override
            public void onImageAvailable(ImageReader reader) {
                mCameraDevice.close();
                mSurfaceView.setVisibility(View.GONE);
                Image image = reader.acquireNextImage();
                ByteBuffer byteBuffer = image.getPlanes()[0].getBuffer();
                byte[] bytes = new byte[byteBuffer.remaining()];
                byteBuffer.get(bytes);
                Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                if (bitmap != null) {
                    iv_show.setImageBitmap(bitmap);
                }

            }
        }, mainHandler);

        mCameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        /**
         * 检查相机权限，有权限就打开相机
         */
        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            try {
                mCameraManager.openCamera(mCameraId, stateCallback, mainHandler);
            } catch (CameraAccessException e) {
                e.printStackTrace();
            }
        }

    }

    private CameraDevice.StateCallback stateCallback = new CameraDevice.StateCallback() {
        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        public void onOpened(@NonNull CameraDevice camera) {
            mCameraDevice = camera;
            takePreview();
        }

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        public void onDisconnected(@NonNull CameraDevice camera) {
            if (null != mCameraDevice) {
                mCameraDevice.close();
                mCameraDevice = null;

            }

        }

        @Override
        public void onError(@NonNull CameraDevice camera, int error) {

            Toast.makeText(mContext, "相机打开失败:  " + error, Toast.LENGTH_SHORT).show();
        }
    };

    /**
     * 预览图片
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void takePreview() {
        try {
            final CaptureRequest.Builder builder = mCameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW);
            builder.addTarget(mSurfaceHolder.getSurface());
            mCameraDevice.createCaptureSession(Arrays.asList(mSurfaceHolder.getSurface(), mImageReader.getSurface()), new CameraCaptureSession.StateCallback() {
                @Override
                public void onConfigured(@NonNull CameraCaptureSession session) {
                    if (mCameraDevice == null)return;
                    mCameraCaptureSession = session;
                    builder.set(CaptureRequest.CONTROL_AF_MODE, CaptureRequest.CONTROL_AF_MODE_CONTINUOUS_PICTURE);
                    builder.set(CaptureRequest.CONTROL_AE_MODE, CaptureRequest.CONTROL_AE_MODE_ON_AUTO_FLASH);
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
    protected void initData() {

    }

    @Override
    protected void initSetListener() {
        findViewById(R.id.btn_take).setOnClickListener(this);

    }

    @Override
    protected void handlerMessage(Message msg) {

    }

    @Override
    public void onClick(View v) {

    }
}
