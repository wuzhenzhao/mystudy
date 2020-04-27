package com.wuzz.demo.integratedway1.config;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;

import javax.jms.ConnectionFactory;

/**
 * User: Wuzhenzhao
 * Date: 2019/12/9
 * Time: 17:05
 * Description:
 * ClassPath:com.wuzz.demo.integratedway1.config.ActiveMqConfig
 */
@Configuration
public class ActiveMqConfig {


    // queue模式的ListenerContainer
    @Bean
    public JmsListenerContainerFactory<?> jmsListenerContainerQueue(ConnectionFactory activeMQConnectionFactory) {
        DefaultJmsListenerContainerFactory bean = new DefaultJmsListenerContainerFactory();
        //使用异步发送
//        ((ActiveMQConnectionFactory) activeMQConnectionFactory).setUseAsyncSend(true);
        //使用异步发送
//        ((ActiveMQConnection)activeMQConnectionFactory).setUseAsyncSend(true);
        bean.setConnectionFactory(activeMQConnectionFactory);
        return bean;
    }


    // topic模式的ListenerContainer
    @Bean
    public JmsListenerContainerFactory<?> jmsListenerContainerTopic(ConnectionFactory activeMQConnectionFactory) {
        DefaultJmsListenerContainerFactory bean = new DefaultJmsListenerContainerFactory();
        bean.setPubSubDomain(true);
        bean.setConnectionFactory(activeMQConnectionFactory);
        return bean;
    }

}
