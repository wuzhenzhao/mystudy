package com.wuzz.demo;

import rx.Observable;
import rx.Observer;
import rx.functions.Action0;
import rx.functions.Func0;

import java.util.concurrent.ExecutionException;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/3/13
 * Time: 18:13
 * Description:
 */
public class RxJavaDemo {

    // ReactiveX Java  响应式编程框架(android）
    // Java stream() java8
    //观察者模式
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        final String[] datas = new String[]{"登录"};

        final Action0 onComplated = new Action0() {
            @Override
            public void call() {
                System.out.println("on Complated");
            }
        };
        //老师（被观察者）
        Observable<String> observable = Observable.defer(new Func0<Observable<String>>() {
            @Override
            public Observable<String> call() {
                Observable observable1 = Observable.from(datas);
                return observable1.doOnCompleted(onComplated);
            }
        });
        //学生(观察者)
        Observer observer = new Observer() {
            @Override
            public void onCompleted() {
                System.out.println("Observer: onCompleted");
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("Observer: onError");
            }

            @Override
            public void onNext(Object o) {
                System.out.println("on Next:" + o);
            }
        };
//        observable.subscribe(observer); //建立订阅关系

        String s = observable.toBlocking().toFuture().get();//建立订阅关系
        System.out.println(s);
    }
}
