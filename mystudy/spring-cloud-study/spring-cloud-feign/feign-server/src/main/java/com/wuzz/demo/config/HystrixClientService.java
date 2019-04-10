package com.wuzz.demo.config;

import com.wuzz.demo.service.ClientService;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/4/10
 * Time: 16:42
 * Description ÃèÊö:
 */
@Component
public class HystrixClientService implements FallbackFactory<ClientService> {
    @Override
    public ClientService create(Throwable throwable) {
        return new ClientService() {
            @Override
            public String hello() {
                return "·þÎñ½µ¼¶¡£¡£¡£¡£";
            }
        };
    }
}
