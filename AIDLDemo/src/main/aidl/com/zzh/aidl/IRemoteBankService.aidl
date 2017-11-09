// IRemoteBankService.aidl
package com.zzh.aidl;
import com.zzh.aidl.User;
import com.zzh.aidl.RemoteClientCallBack;


// Declare any non-default types here with import statements

interface IRemoteBankService {
    boolean depositMoney(int money);
    int drawMoney(int money);
    User getUser();
    void registerClientOberser(RemoteClientCallBack callback);
}
