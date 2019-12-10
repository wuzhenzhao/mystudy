package com.wuzz.demo.integratedway1;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Configuration;

/**
 * User: Wuzhenzhao
 * Date: 2019/12/9
 * Time: 19:30
 * Description:
 * ClassPath:com.wuzz.demo.integratedway1.SecondConsumer
 */
@Configuration
@RabbitListener(queues = "SECOND_DQUEUE")
public class SecondConsumer {

    @RabbitHandler
    public void process(String msg) {
        System.out.println("second Queue received msg : " + msg);
    }
}


