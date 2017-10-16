package com.zzh.main.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.Messenger;
import android.os.RemoteException;

import java.util.List;

/**
 * Created by user on 2017/10/16.
 *
 * @date: 2017/10/16
 * @email: zzh_hz@126.com
 * @QQ: 1299234582
 * @author: zzh
 * @description:
 */
public class BookManagerImpl extends Binder implements IBookManager {
    @Override
    public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        
    }

    @Override
    public List<Book> getBookList() throws RemoteException {
        return null;
    }

    @Override
    public void addBook(Book book) throws RemoteException {

    }

    @Override
    public IBinder asBinder() {
        return this;
    }
}
