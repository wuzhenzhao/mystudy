package com.wuzz.demo.integratedway1.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @description: 类功能描述
 * @author: wuzhenzhao
 * @time 2020/9/4 13:50
 * @since 1.0
 **/
@Configuration
@ConfigurationProperties(prefix = "my.topic")
public class KafkaTopicConfig implements InitializingBean {

    private String topic;

    @Override
    public void afterPropertiesSet() {
        System.setProperty("topics", topic == null ? "testCopyTopic" : topic);
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
}
