package com.wuzz.demo.ServiceImpl;

import com.wuzz.demo.service.UserService;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/8/14
 * Time: 15:47
 * Description 描述:
 */
public class UserServiceImpl implements UserService {
    @Override
    public int register(int id) {
        return id + 1;
    }
}
