package com.wuzz.demo.integratedway1.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${spring.activemq.broker-url}")
    private String brokerUrl;

    @Value("${spring.activemq.user}")
    private String username;

    @Value("${spring.activemq.password}")
    private String password;


    @Bean
    public ConnectionFactory connectionFactory() {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(username, password, brokerUrl);
//        RedeliveryPolicy policy=new RedeliveryPolicy();
//        policy.setUseExponentialBackOff(Boolean.TRUE);
//        policy.setMaximumRedeliveries(2);
//        policy.setInitialRedeliveryDelay(1000L);
//        activeMQConnectionFactory.setRedeliveryPolicy(policy);
        return connectionFactory;


    }

    // queue模式的ListenerContainer
    @Bean
    public JmsListenerContainerFactory<?> jmsListenerContainerQueue(ConnectionFactory connectionFactory) {
        DefaultJmsListenerContainerFactory bean = new DefaultJmsListenerContainerFactory();
        //使用异步发送
//        ((ActiveMQConnectionFactory) activeMQConnectionFactory).setUseAsyncSend(true);
        //使用异步发送
//        ((ActiveMQConnection)activeMQConnectionFactory).setUseAsyncSend(true);
        //手动应答
        bean.setSessionAcknowledgeMode(4);
        bean.setConnectionFactory(connectionFactory);
        return bean;
    }

    // topic模式的ListenerContainer
    @Bean
    public JmsListenerContainerFactory<?> jmsListenerContainerTopic(ConnectionFactory connectionFactory) {
        DefaultJmsListenerContainerFactory bean = new DefaultJmsListenerContainerFactory();
        bean.setPubSubDomain(true);
        bean.setConnectionFactory(connectionFactory);
        return bean;
    }
}
