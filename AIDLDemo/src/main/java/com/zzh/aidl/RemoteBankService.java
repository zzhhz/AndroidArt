package com.zzh.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

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

    private IRemoteBankService.Stub mRemoteBind = new IRemoteBankService.Stub() {
        @Override
        public boolean despoistMoney(int money) throws RemoteException {
            return false;
        }

        @Override
        public int drawMoney(int money) throws RemoteException {
            return 0;
        }
    };


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mRemoteBind;
    }
}
