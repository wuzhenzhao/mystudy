package com.wuzz.demo.integratedway1;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Configuration;

/**
 * User: Wuzhenzhao
 * Date: 2019/12/9
 * Time: 19:30
 * Description:
 * ClassPath:com.wuzz.demo.integratedway1.FirstConsumer
 */
@Configuration
@RabbitListener(queues = "FIRST_QUEUE")
public class FirstConsumer {

    @RabbitHandler
    public void process(String msg) {
        System.out.println("First Queue received msg : " + msg);
    }
}
