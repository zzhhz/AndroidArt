package com.zzh.data.mvvm.click;

import android.util.Log;
import android.view.View;

import com.zzh.data.mvvm.model.LoginUser;

/**
 * Created by user on 2017/10/24.
 *
 * @date: 2017/10/24
 * @email: zzh_hz@126.com
 * @QQ: 1299234582
 * @author: zzh
 * @description: 事件处理
 */
public class HandleEvent {
    public static final String TAG = "------";
    public void onClickOne(View v, LoginUser binding) {
        if (binding == null) {
            Log.d(TAG, "onClickOne: ViewDataBinding: " + binding);
            return;
        }
        Log.d("------:", "" + binding.getUserName() + ",  " + binding.getPassword()+", "+v);
    }
}
