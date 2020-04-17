package com.wuzz.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/3/13
 * Time: 18:13
 * Description:
 */
@SpringBootApplication
@EnableConfigServer
public class ConfigServerApp {
    private final static Logger log = LoggerFactory.getLogger(ConfigServerApp.class);

    public static void main(String[] args) {
        SpringApplication.run(ConfigServerApp.class, args);
        log.info("服务启动成功");

    }

//    @Bean
//    public EnvironmentRepository newEnvironmentRepository(){
//        return new EnvironmentRepository() {
//            @Override
//            public Environment findOne(String application, String profile, String label) {
//                Environment environment =new Environment(application, profile);
//                List<PropertySource> propertySourceList = environment.getPropertySources();
//                Map<String, String> map = new HashMap<>();
//                map.put("name", "garine-define");
//                PropertySource propertySource = new PropertySource("map", map);
//                propertySourceList.add(propertySource);
//                return environment;
//            }
//        };
//    }
}
