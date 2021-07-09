package com.wuzz.demo.integratedway1;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * User: Wuzhenzhao
 * Date: 2019/12/9
 * Time: 19:33
 * Description:
 * ClassPath:com.wuzz.demo.integratedway1.MyProducer
 */
@Component
public class MyProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send() {
        for (int i = 1; i <= 2; i++) {
            rabbitTemplate.convertAndSend("DIRECT_EXCHANGE", "wuzz.test", "DIRECT_EXCHANGE message" + i);

        }
//        rabbitTemplate.convertAndSend("TOPIC_EXCHANGE", "hangzhou.wuzz.test", "TOPIC_EXCHANGE hangzhou message");
//        rabbitTemplate.convertAndSend("TOPIC_EXCHANGE", "wenzhou.wuzz.test", "TOPIC_EXCHANGE wenzhou message");
//        rabbitTemplate.convertAndSend("FANOUT_EXCHANGE", "", "FANOUT_EXCHANGE message");

    }
}
