package com.wuzz.demo.observer;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/3/22
 * Time: 12:13
 * Description 描述:
 */
public class Subject extends EventLisenter {

    //通常的话，采用动态代理来实现监控，避免了代码侵入
    public void pay(){
        System.out.println("调用支付的方法");
        //触发事件
        trigger(SubjectEventType.ON_PAY);
    }

    public void cancel(){
        System.out.println("调用取消订单的方法");
        trigger(SubjectEventType.ON_CANCEL);
    }
}
