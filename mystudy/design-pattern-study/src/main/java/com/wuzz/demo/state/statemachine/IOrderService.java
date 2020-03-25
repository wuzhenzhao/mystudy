package com.wuzz.demo.state.statemachine;

import java.util.Map;

/**
 * @description:
 * @author: Wuzhenzhao@hikvision.com.cn
 * @time 2020/3/25 11:43
 * @since 1.0
 **/
public interface IOrderService {
    //创建新订单
    Order create();

    //发起支付
    Order pay(int id);

    //订单发货
    Order deliver(int id);

    //订单收货
    Order receive(int id);

    //获取所有订单信息
    Map<Integer, Order> getOrders();

}
