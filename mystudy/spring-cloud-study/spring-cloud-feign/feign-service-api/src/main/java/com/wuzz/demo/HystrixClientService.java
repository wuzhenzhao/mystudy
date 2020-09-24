package com.wuzz.demo;

import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/4/10
 * Time: 16:42
 * Description ����:
 */
@Component
public class HystrixClientService implements FallbackFactory<ClientService> {
    @Override
    public ClientService create(Throwable throwable) {
        return new ClientService() {
            @Override
            public String hello(String id) {
                System.out.println("feign 服务降级");
                return "feign 服务降级";
            }
        };
    }
}
