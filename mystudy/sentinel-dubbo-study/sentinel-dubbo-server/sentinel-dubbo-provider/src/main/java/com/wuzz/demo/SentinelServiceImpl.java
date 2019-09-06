package com.wuzz.demo;

import org.apache.dubbo.config.annotation.Service;

import java.time.LocalDateTime;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/5/5
 * Time: 16:51
 * Description 描述:
 */
@Service//把当前服务发布成dubbo服务
public class SentinelServiceImpl implements SentinelService {

    @Override
    public String sayHello(String txt) {
        return "hello world :" + LocalDateTime.now();
    }
}
