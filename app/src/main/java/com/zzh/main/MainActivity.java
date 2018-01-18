package com.zzh.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.zzh.main.model.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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

        User user1 = new User("1", "张");
        User user2 = new User("2", "王");
        User user3 = new User("3", "李");
        User user4 = new User("4", "赵");
        User user5 = new User("5", "刘");
        User user6 = new User("6", "公孙");
        User user7 = new User("7", "欧阳");
        User user8 = new User("8", "轩辕");
        List<User> list = new ArrayList<>();
        list.add(user1);
        list.add(user2);
        list.add(user1);
        list.add(user3);
        list.add(user4);
        list.add(user5);
        list.add(user6);
        list.add(user7);
        list.add(user8);
        Collections.sort(list);


        Log.d("----------", "initViews: " + Arrays.toString(list.toArray()));
    }


}
