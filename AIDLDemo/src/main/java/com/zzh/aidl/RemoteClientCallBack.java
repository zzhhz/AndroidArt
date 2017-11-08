package com.zzh.aidl;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by user on 2017/11/8.
 *
 * @date: 2017/11/8
 * @email: zzh_hz@126.com
 * @QQ: 1299234582
 * @author: zzh
 * @description:
 */
public class RemoteClientCallBack implements Parcelable {

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    public RemoteClientCallBack() {
    }

    protected RemoteClientCallBack(Parcel in) {
    }

    public static final Creator<RemoteClientCallBack> CREATOR = new Creator<RemoteClientCallBack>() {
        @Override
        public RemoteClientCallBack createFromParcel(Parcel source) {
            return new RemoteClientCallBack(source);
        }

        @Override
        public RemoteClientCallBack[] newArray(int size) {
            return new RemoteClientCallBack[size];
        }
    };
}
