package com.wuzz.demo.event;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @description: 类功能描述
 * @author: wuzhenzhao
 * @time 2020/5/9 19:31
 * @since 1.0
 **/
@Component
public class OrderListener {

    @EventListener
    public void orderListener(Order order){
        System.out.println(this.getClass().getName() + " -- 监听一个订单");
    }
//事件传播机制
//    当我们监听一个事件处理完成时，还需要发布另一个事件，一般我们想到的是调用ApplicationEventPublisher#publishEvent发布事件方法，
//    但Spring提供了另一种更加灵活的新的事件继续传播机制，监听方法返回一个事件，也就是方法的返回值就是一个事件对象。
    @EventListener
    public OrderCreateEvent orderReturnEvent(Order order){
        System.out.println(this.getClass().getName() + " -- 监听一个订单,返回一个新的事件 OrderCreateEvent");
        return new OrderCreateEvent(this,order);
    }
}
