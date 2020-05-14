package com.wuzz.demo;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
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
    public String sayHello(String name) {
        try {
            Thread.sleep(500); //添加这个和不添加这个的影响
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("begin execute sayHello:" + name);
        return "Hello World:" + name + "->timer:" + LocalDateTime.now();
    }
}
