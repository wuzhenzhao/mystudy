package com.wuzz.demo.adapter;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/3/22
 * Time: 10:51
 * Description 描述:定义一个service模拟线上已定的稳定到不可变的登录注册业务接口
 */
public interface ISigninService {

    //注册方法
    ResultMsg regist(String username, String password);

    // 登录的方法
    ResultMsg login(String username, String password);
}
