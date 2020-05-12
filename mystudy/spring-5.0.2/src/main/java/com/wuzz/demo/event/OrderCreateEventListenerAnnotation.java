package com.wuzz.demo.event;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @description: 类功能描述
 * @author: wuzhenzhao
 * @time 2020/5/9 19:26
 * @since 1.0
 **/
@Component
public class OrderCreateEventListenerAnnotation {

    @EventListener
    public void createOrderEvent(OrderCreateEvent event) {
        System.out.println(this.getClass().getName() + "--订单创建事件，@EventListener注解实现，orderNo:" + event.getOrder().getOrderNo());
    }

//    上面的监听事件都是同步触发的，如果想异步的怎么办？只需要两步：
//    启动类上添加 @EnableAsync注解，开启异步支持。
//    监听方法上添加 @Async注解
}
