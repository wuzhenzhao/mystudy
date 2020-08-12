package com.wuzz.demo.entity;


import lombok.Data;

/**
 * @description: 类功能描述
 * @author: wuzhenzhao
 * @time 2020/8/10 19:59
 * @since 1.0
 **/
@Data
public class OrderItem {

    private long userId;
    private long orderItemId;
    private long orderId;
    private String name;
    private long price;

}
