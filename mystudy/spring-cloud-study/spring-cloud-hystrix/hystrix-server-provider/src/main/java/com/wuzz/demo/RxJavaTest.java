package com.wuzz.demo;

import rx.Observable;
import rx.Subscriber;

/**
 * @description:
 * @author: Wuzhenzhao@hikvision.com.cn
 * @time 2020/4/14 20:39
 * @since 1.0
 **/
public class RxJavaTest {
    public static void main(String[] args) {

        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {

            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("Hello RxJava");
                subscriber.onNext("I am程序猿DD");
                subscriber.onCompleted();
            }
        });

        //创建订阅者subscriber
        Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                System.out.println("Subscriber : " + s);
            }
        };
        //订阅
        observable.subscribe(subscriber);
    }
}
