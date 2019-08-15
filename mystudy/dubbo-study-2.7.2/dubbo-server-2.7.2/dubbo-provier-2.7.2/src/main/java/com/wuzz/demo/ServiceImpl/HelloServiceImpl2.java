package com.wuzz.demo.ServiceImpl;

import com.wuzz.demo.service.HelloService;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/3/22
 * Time: 15:39
 * Description 描述: 服务实现
 */
public class HelloServiceImpl2 implements HelloService {

    @Override
    public String sayHello(String msg) {
        return "Hello2:"+msg;
    }
}
