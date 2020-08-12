package com.wuzz.demo.dao;

import com.wuzz.demo.entity.Order;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @description: 类功能描述
 * @author: wuzhenzhao
 * @time 2020/8/10 19:59
 * @since 1.0
 **/
@Repository
public interface OrderDao {

    long addOne(Order order);

    Order selectOne(@Param("order_id") long orderId, @Param("user_id") int userId);

    List<Order> getOrderByUserId(long id);

}
