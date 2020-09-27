package com.wuzz.demo.service;

import com.wuzz.demo.dao.OrderItemDao;
import com.wuzz.demo.entity.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/3/13
 * Time: 18:13
 * Description:
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
