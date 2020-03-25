package com.wuzz.demo.state.statemachine;

/**
 * @description:
 * @author: Wuzhenzhao@hikvision.com.cn
 * @time 2020/3/25 11:39
 * @since 1.0
 **/
public enum OrderStatus {
    // 待支付
    WAIT_PAYMENT,
    //待发货
    WAIT_DELIVER,
    //待收货
    WAIT_RECEIVE,
    //订单结束
    FINISH;
}
