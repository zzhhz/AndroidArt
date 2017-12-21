package com.zzh.rxjavaretrofitdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by ZZH on 2017/12/21
 *
 * @date: 2017/12/21 上午11:03
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
        testRxJava();
    }

    private void testRxJava() {
        //发送事件
        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                System.out.println(Thread.currentThread().getName() + "--subscribe---");
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
                //e.onError(new Throwable("发送一个错误信息"));
                e.onNext(4);
                //e.onComplete();
            }
        });
        Consumer<Integer> consumer = new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                System.out.println(Thread.currentThread().getName() + "--accept---" + integer);
            }
        };

        Consumer<Throwable> error = new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                System.out.println(Thread.currentThread().getName() + "--error---" + throwable.getMessage());
            }
        };
        Action action = new Action() {
            @Override
            public void run() throws Exception {

                System.out.println(Thread.currentThread().getName() + "--run---");
            }
        };

        //关联订阅关系
        observable.subscribeOn(Schedulers.single()).observeOn(AndroidSchedulers.mainThread()).subscribe(consumer, error, action);

    }
}
