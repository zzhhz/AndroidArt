package com.zzh.rxjavaretrofitdemo;

import org.junit.Test;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }


    @Test
    public void testRXJava() {
        //发送事件
        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                System.out.println(Thread.currentThread().getName() + "--subscribe---");
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
                e.onError(new Throwable("---发送错误--"));
                e.onNext(4);
                e.onComplete();
            }
        });

        //接收事件
        Observer<Integer> observer = new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                System.out.println(Thread.currentThread().getName() + "--onSubscribe---");
            }

            @Override
            public void onNext(Integer integer) {
                System.out.println(Thread.currentThread().getName() + "--onNext---" + integer);
            }

            @Override
            public void onError(Throwable e) {
                System.out.println(Thread.currentThread().getName() + "--onError---" + e.getMessage());
            }

            @Override
            public void onComplete() {
                System.out.println(Thread.currentThread().getName() + "--onComplete---");
            }
        };
        //关联订阅关系
        observable.subscribeOn(Schedulers.io()).observeOn(Schedulers.newThread()).subscribe(observer);

    }

    @Test
    public void testRXJava2() {
        //发送事件
        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                System.out.println(Thread.currentThread().getName() + "--subscribe---");
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
                e.onError(new Throwable("发送一个错误信息"));
                e.onNext(4);
                e.onComplete();
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

                System.out.println(Thread.currentThread().getName() + "--action---");
            }
        };

        //关联订阅关系
        observable.subscribeOn(Schedulers.io()).observeOn(Schedulers.newThread()).subscribe(consumer, error,action);
    }

    @Test
    public void testRXJava3() {
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
                e.onComplete();
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
        observable.subscribeOn(Schedulers.single()).observeOn(Schedulers.newThread()).subscribe(consumer, error, action);
    }

    @Test
    public void testRXJava4() {
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
                e.onComplete();
            }
        });
        //接收事件
        Observer<Integer> observer = new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                System.out.println(Thread.currentThread().getName() + "--onSubscribe---");
            }

            @Override
            public void onNext(Integer integer) {
                System.out.println(Thread.currentThread().getName() + "--onNext---" + integer);
            }

            @Override
            public void onError(Throwable e) {
                System.out.println(Thread.currentThread().getName() + "--onError---" + e.getMessage());
            }

            @Override
            public void onComplete() {
                System.out.println(Thread.currentThread().getName() + "--onComplete---");
            }
        };

        //关联订阅关系
        observable.subscribeOn(Schedulers.io()).observeOn(Schedulers.newThread()).subscribe(observer);
    }
    @Test
    public void testThread(){
        System.out.println(Thread.currentThread().getName() + "-----");
        new Thread("Thread - 1"){
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "-----");
            }
        }.start();
        new Thread("Thread - 2"){
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "-----");
            }
        }.start();
        new Thread("Thread - 3"){
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "-----");
            }
        }.start();
        new Thread("Thread - 4"){
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "-----");
            }
        }.start();
        new Thread("Thread - 5"){
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "-----");
            }
        }.start();
        new Thread("Thread - 6"){
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "-----");
            }
        }.start();
    }
}