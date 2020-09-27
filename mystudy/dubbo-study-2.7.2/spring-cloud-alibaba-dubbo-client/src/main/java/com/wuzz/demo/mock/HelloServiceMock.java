package com.wuzz.demo.mock;

import com.wuzz.demo.api.HelloService;

/**
 * create-date: 2019/7/21-21:28
 * 降级处理的类
 */
public class HelloServiceMock implements HelloService {
    @Override
    public String sayHello() {
        return "服务端发生异常， 被降解了。返回兜底数据。。。";
    }
}
