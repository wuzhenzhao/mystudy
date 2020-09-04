package com.wuzz.demo.integratedway1.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Configuration;

/**
 * @description: 类功能描述
 * @author: wuzhenzhao
 * @time 2020/9/4 13:50
 * @since 1.0
 **/
@Configuration
public class KafkaTopicConfig implements InitializingBean {

    @Override
    public void afterPropertiesSet() {
        System.setProperty("topics", "myTopics");
    }
}
