package com.wuzz.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/3/13
 * Time: 18:13
 * Description:
 */
@SpringBootApplication
@EnableZuulProxy
@EnableDiscoveryClient
//@EnableConfigurationProperties({FilterConfiguration.class})
public class ZuulApp {
    private final static Logger log = LoggerFactory.getLogger(ZuulApp.class);

    public static void main(String[] args) {
        SpringApplication.run(ZuulApp.class, args);
        log.info("服务启动成功");
    }

//    @Bean
//    public FilterLoader filterLoader(FilterConfiguration filterConfiguration) {
//        FilterLoader filterLoader = FilterLoader.getInstance();
//        filterLoader.setCompiler(new GroovyCompiler());
//        try {
//            FilterFileManager.setFilenameFilter(new GroovyFileFilter());
//            FilterFileManager.init(
//                    filterConfiguration.getInterval(),
//                    filterConfiguration.getRoot() + "/pre",
//                    filterConfiguration.getRoot() + "/post");
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//        return filterLoader;
//    }
}
