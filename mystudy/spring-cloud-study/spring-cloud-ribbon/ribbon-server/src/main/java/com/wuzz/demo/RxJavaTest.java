package com.wuzz.demo;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;

/**
 * @description:
 * @author: Wuzhenzhao
 * @time 2020/4/14 20:39
 * @since 1.0
 **/
public class RxJavaTest {
    public static void main(String[] args) {
        //例子1
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
                System.out.println("Subscriber : onCompleted");
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
        //例子2
        System.out.println("=========================================");
        //正常情况是先产生数据，再注册，再消费。
        Observable.just(dataProducer()).doOnSubscribe(() -> {
            System.out.println("Subscribe!");
        }).subscribe(s -> {
            System.out.println("Consume Data :" + s);
        });

        //使用了defer之后，是先注册，再生产数据，再消费。
        Observable.defer(() -> {
            return Observable.just(dataProducer());
        }).doOnSubscribe(() -> {
            System.out.println("Subscribe!");
        }).subscribe(s -> {
            System.out.println("Consume Data :" + s);
        });

        System.out.println("=========================================");
        //我们不关心onError和onComplete，所以只需要第一个参数就可以
        //例子3
        Observable<String> myObservable = Observable.just("Hello, world! just");

        Action1<String> onNextAction = new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println(s);
            }
        };

        myObservable.subscribe(onNextAction);


    }

    private static String dataProducer() {
        String str = "Hello,RxJava!";
        System.out.println("Produce Data :" + str);
        return str;
    }
}
