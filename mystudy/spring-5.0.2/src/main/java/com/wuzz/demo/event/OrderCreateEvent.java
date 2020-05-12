package com.wuzz.demo.event;

import org.springframework.context.ApplicationEvent;

/**
 * @description: 类功能描述
 * @author: wuzhenzhao
 * @time 2020/5/9 19:19
 * @since 1.0
 **/
public class OrderCreateEvent extends ApplicationEvent {

    private final Order order;

    public OrderCreateEvent(Object source, Order order) {
        super(source);
        this.order = order;
    }

    public Order getOrder() {
        return order;
    }
}
