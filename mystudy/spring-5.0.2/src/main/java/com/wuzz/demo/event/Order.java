package com.wuzz.demo.event;

import java.util.Date;

/**
 * @description: 类功能描述
 * @author: wuzhenzhao
 * @time 2020/5/9 19:19
 * @since 1.0
 **/
public class Order {

    private String orderNo;

    private String orderStatus;

    private String goods;

    private Date createTime;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getGoods() {
        return goods;
    }

    public void setGoods(String goods) {
        this.goods = goods;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
