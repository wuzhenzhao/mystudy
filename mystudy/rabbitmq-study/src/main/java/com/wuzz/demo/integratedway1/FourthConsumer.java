package com.wuzz.demo.integratedway1;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Configuration;

/**
 * User: Wuzhenzhao
 * Date: 2019/12/9
 * Time: 19:31
 * Description:
 * ClassPath:com.wuzz.demo.integratedway1.FourthConsumer
 */
@Configuration
@RabbitListener(queues = "FOURTH_DQUEUE")
public class FourthConsumer {

    @RabbitHandler
    public void process(String msg) {
        System.out.println("fourth Queue received msg : " + msg);
    }
}
