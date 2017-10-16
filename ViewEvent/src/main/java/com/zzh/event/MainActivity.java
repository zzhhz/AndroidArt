package com.zzh.event;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * Created by ZZH on 2017/10/16
 *
 * @date: 2017/10/16 下午3:26
 * @email: zzh_hz@126.com
 * @QQ: 1299234582
 * @author: zzh
 * @Description: View的位置主要由它的四个顶点来决定的。
 * <p>
 *  分别对应于view的四个属性：
 *      1. top 左上角纵坐标；
 *      2. left 左上角横坐标；
 *      3. right 右下角横坐标；
 *      4. bottom 右下角纵坐标
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}
