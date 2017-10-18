package com.zzh.view.principle;

import android.graphics.drawable.ShapeDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
/**
 * Created by ZZH on 2017/10/18
 *
 * @date: 2017/10/18 下午3:41
 * @email: zzh_hz@126.com
 * @QQ: 1299234582
 * @author: zzh
 * @Description: view 的工作原理
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ShapeDrawable drawable = new ShapeDrawable();
    }
}
