package com.zzh.data;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.zzh.data.databinding.ActivityMainBinding;
import com.zzh.data.mvvm.click.HandleEvent;
import com.zzh.data.mvvm.model.LoginUser;

/**
 * Created by ZZH on 2017/10/24
 *
 * @date: 2017/10/24 上午9:19
 * @email: zzh_hz@126.com
 * @QQ: 1299234582
 * @author: zzh
 * @Description: data binding
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        LoginUser user = new LoginUser("张三", "123456zxc");
        binding.setUser(user);
        HandleEvent event = new HandleEvent();
        binding.setHandle(event);
    }
}
