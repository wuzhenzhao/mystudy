package com.wuzz.demo.entity;

import lombok.Data;

import java.util.Date;
/**
 * @description: 类功能描述
 * @author: wuzhenzhao
 * @time 2020/8/10 19:59
 * @since 1.0
 **/
@Data
public class Order {

    private long orderId;
    private long userId;
    private Date createTime;
    private long totalPrice;

}
