// User.aidl
package com.zzh.aidl;

// Declare any non-default types here with import statements

interface RemoteClientCallBack{
    void transferToClientByServer(String transferData);
}