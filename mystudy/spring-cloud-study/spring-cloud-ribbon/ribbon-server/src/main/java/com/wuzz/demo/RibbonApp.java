package com.wuzz.demo;

import com.wuzz.demo.myrule.MyRule_ZY;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/3/13
 * Time: 18:13
 * Description:
 */
@EnableDiscoveryClient
@SpringBootApplication
//自定义负载均衡算法 自定义配置类不能跟主启动类一个包或在子包下面
//name: 表示对哪个服务采用自定义算法
//configuration:负载算法类
@RibbonClient(name="cloud-provider")
public class RibbonApp {
    private final static Logger log = LoggerFactory.getLogger(RibbonApp.class);

    public static void main(String[] args) {
        SpringApplication.run(RibbonApp.class,args);
        log.info("服务启动成功");

    }
}
