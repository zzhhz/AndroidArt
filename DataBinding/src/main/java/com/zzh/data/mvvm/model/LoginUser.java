package com.zzh.data.mvvm.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.zzh.data.BR;
import com.zzh.data.R;

/**
 * Created by user on 2017/10/24.
 *
 * @date: 2017/10/24
 * @email: zzh_hz@126.com
 * @QQ: 1299234582
 * @author: zzh
 * @description:
 */
public class LoginUser extends BaseObservable {
    private String userName;
    private String age;
    private String password;

    public LoginUser() {
    }

    public LoginUser(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    @Bindable
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
        notifyPropertyChanged(BR.userName);
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Bindable
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        notifyPropertyChanged(BR.password);
    }


}
