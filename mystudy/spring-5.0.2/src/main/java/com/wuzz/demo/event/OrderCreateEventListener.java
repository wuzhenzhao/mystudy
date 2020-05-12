package com.wuzz.demo.event;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @description: 类功能描述
 * @author: wuzhenzhao
 * @time 2020/5/9 19:21
 * @since 1.0
 **/
@Component
public class OrderCreateEventListener implements ApplicationListener<OrderCreateEvent> {


    @Override
    public void onApplicationEvent(OrderCreateEvent event) {
        System.out.printf(this.getClass().getName() + " -- ApplicationListener 接口实现，订单号[%s]：,锁定商品[%s]\n",
                event.getOrder().getOrderNo(), event.getOrder().getGoods());
    }
}
