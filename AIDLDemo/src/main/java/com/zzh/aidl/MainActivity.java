package com.zzh.aidl;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";


    public void onClickView(View v) {
        switch (v.getId()) {
            case R.id.button:
                try {
                    boolean  isDesMoney = iRemoteBankService.depositMoney(5);
                    Log.d(TAG,"isDesMoney1 = "+isDesMoney);
                    isDesMoney = iRemoteBankService.depositMoney(-5);
                    Log.d(TAG,"isDesMoney2 = "+isDesMoney);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.button2:
                try {
                    int  drawMoney = iRemoteBankService.drawMoney(5);
                    Log.d(TAG,"drawMoney = "+drawMoney);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.button3:
                User user = null;
                try {
                    user = iRemoteBankService.getUser();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                Log.d(TAG,"user = "+user.toString());
                break;
            case R.id.button4:
                RemoteBankService.bindService(this, serviceConnection);
                break;
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RemoteBankService.doUnbindService(this, serviceConnection);
    }

    private IRemoteBankService iRemoteBankService;
    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {//建立通信链接成功
            Log.d(TAG, "onServiceConnected pid = " + android.os.Process.myPid());
            iRemoteBankService = IRemoteBankService.Stub.asInterface(service);//跨进程的处理方式
//            iRemoteBankService = (IRemoteBankService)service;//统一进程的处理方式
            try {
                iRemoteBankService.registerClientOberser(remoteClientCallBack);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {//断开链接
            Log.d(TAG, "onServiceDisconnected pid = " + android.os.Process.myPid());
            iRemoteBankService = null;
        }
    };

    private RemoteClientCallBack.Stub remoteClientCallBack = new RemoteClientCallBack.Stub() {
        @Override
        public void transferToClientByServer(final String transferData) throws RemoteException {
            Log.d(TAG, "transferData = " + transferData +
                    " client Pid = " + android.os.Process.myPid());
            //如果是service通过handler调用的这个的，由于service的进程调用，所以这个回调不是在
            //主线程而是工作线程中，直接更新或toast会抛出如下异常，所以定要在主线程中更新
            //Uncaught remote exception! (Exceptions are not yet supported across processes.)
            runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    Toast.makeText(MainActivity.this, transferData, Toast.LENGTH_SHORT).show();
                }
            });

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
