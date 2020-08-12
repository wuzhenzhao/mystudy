package com.wuzz.demo.service;

import com.wuzz.demo.dao.OrderItemDao;
import com.wuzz.demo.entity.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: qingshan
 * @Description: 咕泡学院，只为更好的你
 */
@Service
public class OrderItemService {
    @Autowired
    private OrderItemDao orderItemDao;

    public long addOne(OrderItem item) {
        this.orderItemDao.addOne(item);
        return item.getOrderItemId();
    }


}
