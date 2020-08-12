package com.wuzz.demo;

import com.wuzz.demo.dao.OrderDao;
import com.wuzz.demo.dao.OrderItemDao;
import com.wuzz.demo.entity.Order;
import com.wuzz.demo.entity.OrderItem;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderTest {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private OrderItemDao orderItemDao;

    /**
     * 分库分表插入数据测试
     * 对userId取模
     */
    @Test
    public void insertData() throws Exception {
        // 插入订单
        Order order = new Order();
        order.setUserId(2673);
        order.setTotalPrice(666);
        order.setCreateTime(new Date());
        this.orderDao.addOne(order);
        log.info("插入订单id:{}", order.getOrderId());

        // 插入订单明细
        OrderItem item = new OrderItem();
        item.setOrderId(order.getOrderId());
        item.setName("huawei");
        item.setPrice(333);
        item.setUserId(2673);
        this.orderItemDao.addOne(item);
        log.info("插入订单明细id：{}", item.getOrderItemId());
    }

    /**
     * 查询用户订单
     */
    @Test
    public void selectOrder() {
        List<Order> orders = this.orderDao.getOrderByUserId(2673);
        log.info("查询用户订单结果为：{}", Arrays.toString(orders.toArray()));
    }

    /**
     * 关联查询
     * 没有使用分片键，会产生笛卡尔积查询
     */
    @Test
    public void selectOrderItem() {
        List<OrderItem> orderItems = this.orderItemDao.getOrderItemByPrice(666);
        log.info("查询到结果为：{}", Arrays.toString(orderItems.toArray()));
    }
}

