package com.wuzz.demo.integratedway1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * User: Wuzhenzhao
 * Date: 2019/12/9
 * Time: 19:33
 * Description:
 * ClassPath:com.wuzz.demo.integratedway1.MyProducer
 */
@Component
public class ProducerMessageServiceImpl implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnCallback {

    private static Logger log = LoggerFactory.getLogger(ProducerMessageServiceImpl.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostConstruct
    public void init() {
        //消息发送失败返回到队列中, yml需要配置 publisher-returns: true
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setConfirmCallback(this);//指定 ConfirmCallback
        rabbitTemplate.setReturnCallback(this);
    }


    public void send() {

        rabbitTemplate.convertAndSend("DIRECT_EXCHANGE", "wuzz.test", "DIRECT_EXCHANGE message2");

    }

    //通过实现 ConfirmCallback 接口，消息发送到 Broker 后触发回调，
    // 确认消息是否到达 Broker 服务器，也就是只确认是否正确到达 Exchange 中
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if (ack) {
            log.info("---- confirm ----ack==true  cause=" + cause);
        } else {
            log.info("---- confirm ----ack==false  cause=" + cause);
        }
    }

    //启动消息失败返回，比如路由不到队列时触发回调
    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        log.info("---- returnedMessage ----replyCode=" + replyCode + " replyText=" + replyText + " ");
    }
}
