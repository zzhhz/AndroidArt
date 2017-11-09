// IRemoteBankService.aidl
package com.zzh.aidl;

// Declare any non-default types here with import statements

interface IRemoteBankService {
    boolean despoistMoney(int money);
    int drawMoney(int money);
    //User getUser();
    //void registerClientOberser(RemoteClientCallBack callback);
}
