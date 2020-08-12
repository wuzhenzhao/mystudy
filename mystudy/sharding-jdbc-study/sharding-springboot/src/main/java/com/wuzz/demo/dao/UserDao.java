package com.wuzz.demo.dao;

import com.wuzz.demo.entity.User;
import org.springframework.stereotype.Repository;

/**
 * @description: 类功能描述
 * @author: wuzhenzhao
 * @time 2020/8/10 19:59
 * @since 1.0
 **/
@Repository
public interface UserDao {

    void addOne(User user);

    User getOneById(long id);
}
