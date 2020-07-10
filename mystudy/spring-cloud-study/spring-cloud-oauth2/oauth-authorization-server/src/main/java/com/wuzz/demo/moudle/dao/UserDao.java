package com.wuzz.demo.moudle.dao;

import com.wuzz.demo.moudle.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @description: 类功能描述
 * @author: wuzhenzhao
 * @time 2020/7/10 14:54
 * @since 1.0
 **/
@Repository
public interface UserDao {

    User selectUserByUsername(String UserName);
}
