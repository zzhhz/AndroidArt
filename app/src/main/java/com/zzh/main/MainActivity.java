package com.zzh.main;

import android.os.Bundle;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;

import com.zzh.main.aidl.Book;
import com.zzh.main.aidl.IBookManager;

import java.util.List;

/**
 * Created by ZZH on 2017/10/16
 *
 * @date: 2017/10/16 上午11:33
 * @email: zzh_hz@126.com
 * @QQ: 1299234582
 * @author: zzh
 * @Description:
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        IBookManager.Stub stub = new IBookManager.Stub() {
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
        };
        try {
            stub.getBookList();

        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }


}
