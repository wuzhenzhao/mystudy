package com.wuzz.demo.event;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @description: 类功能描述
 * @author: wuzhenzhao
 * @time 2020/5/9 19:20
 * @since 1.0
 **/
@Service
public class OrderService implements ApplicationEventPublisherAware {

    private ApplicationEventPublisher applicationEventPublisher;

    /**
     * 订单保存
     */
    @Transactional
    public void save(){
        String orderNo = getOrderNo();
        Order order = new Order();
        order.setOrderNo(orderNo);
        order.setOrderStatus("待付款");
        order.setCreateTime(new Date());
        order.setGoods("手机");
        System.out.println("订单保存成功：" + order.toString());

        //发布事件
//        applicationEventPublisher.publishEvent(new OrderCreateEvent(this,order));
        applicationEventPublisher.publishEvent(order);

    }

    private String getOrderNo() {
        String millis = String.valueOf(System.currentTimeMillis());
        String str = millis.substring(millis.length() - 7, millis.length() - 1);
        return LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")) + str;
    }


    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }
}
