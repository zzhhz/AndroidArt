package com.zzh.aidl;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.os.RemoteException;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by user on 2017/11/9.
 *
 * @date: 2017/11/9
 * @email: zzh_hz@126.com
 * @QQ: 1299234582
 * @author: zzh
 * @description:
 */
public class RemoteBankService extends Service {
    public static final String TAG = "RemoteBankService";
    private RemoteClientCallBack mClientCallBack;

    /**
     * 启动服务
     *
     * @param ctx
     * @param conn
     */
    public static void bindService(Context ctx, ServiceConnection conn) {
        Intent intent = new Intent(ctx, RemoteBankService.class);
        ctx.bindService(intent, conn, Context.BIND_AUTO_CREATE);
    }

    /**
     * 停止服务
     *
     * @param context
     * @param connection
     */
    public static void doUnbindService(Context context, ServiceConnection connection) {
        if (connection != null) {
            context.unbindService(connection);
            context.stopService(new Intent(context, RemoteBankService.class));
        }
    }


    private IRemoteBankService.Stub mRemoteBind = new IRemoteBankService.Stub() {
        /**
         * 存钱
         * @param money 数值
         * @return
         * @throws RemoteException
         */
        @Override
        public boolean depositMoney(int money) throws RemoteException {
            if (money > 0) {
                //
                return true;
            }
            return false;
        }

        @Override
        public int drawMoney(int money) throws RemoteException {
            Log.d(TAG, "drawMoney pid = " + android.os.Process.myPid());
            mClientCallBack.transferToClientByServer("当前用户取钱成功 取出 ：" + money + "当前进程Id = " + android.os.Process.myPid());
            return money;
        }

        @Override
        public User getUser() throws RemoteException {
            return new User("张三", "" + System.currentTimeMillis(), Process.myPid() + "");
        }

        @Override
        public void registerClientOberser(RemoteClientCallBack callback) throws RemoteException {
            mClientCallBack = callback;
            Message message = Message.obtain();
            message.obj = callback;
            timeHandler.sendMessage(message);

        }
    };


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mRemoteBind;
    }


    private TimeHandler timeHandler = new TimeHandler();

    static class TimeHandler extends Handler {
        public TimeHandler() {
            Looper looper = Looper.myLooper();
            if (null == looper) {
                Looper.prepare();
                Looper.loop();
            }
        }

        @Override
        public void handleMessage(Message msg) {
            if (null != msg.obj) {
                RemoteClientCallBack clientCallBackInstance = (RemoteClientCallBack) msg.obj;
                try {
                    clientCallBackInstance.transferToClientByServer(
                            "已延期10s后发送 当前进程Id = " + Process.myPid());
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
