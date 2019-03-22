package com.wuzz.demo.observer;

import java.lang.reflect.Method;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/3/22
 * Time: 12:17
 * Description 描述:
 */
public class ObserverTest {

    public static void main(String[] args) {
        try{
            //观察者
            Observer observer = new Observer();
            //观察者的具体动作
            Method advice = Observer.class.getMethod("advice", new Class<?>[]{Event.class});

            //收银台调用了支付或者取消订单
            Subject subject = new Subject();
            subject.addLisenter(SubjectEventType.ON_PAY,observer,advice);
            // 具体的执行方法可以自己定义，触发不同事件执行不同的方法，也可以统一处理
//            subject.addLisenter(SubjectEventType.ON_CANCEL,observer,advice);

            subject.pay();
//            subject.cancel();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
