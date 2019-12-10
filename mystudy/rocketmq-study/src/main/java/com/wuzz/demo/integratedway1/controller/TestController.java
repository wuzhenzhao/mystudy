package com.wuzz.demo.integratedway1.controller;

import com.wuzz.demo.integratedway1.ParamConfigService;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * User: Wuzhenzhao
 * Date: 2019/12/9
 * Time: 17:10
 * Description:
 * ClassPath:com.wuzz.demo.integratedway1.controller.TestController
 */
@RestController
public class TestController {

    @Autowired
    private DefaultMQProducer defaultMQProducer;

    @Autowired
    private ParamConfigService paramConfigService;

    @RequestMapping(value = "/testStringQueue.json", method = {RequestMethod.GET})
    public SendResult testStringQueue() {
        // 可以不使用Config中的Group
        defaultMQProducer.setProducerGroup(paramConfigService.platGroup);
        SendResult sendResult = null;
        String msgInfo = "rocketmq  message 1";
        try {
            Message sendMsg = new Message(paramConfigService.platTopic,
                    paramConfigService.accountTag, msgInfo.getBytes());
            sendResult = defaultMQProducer.send(sendMsg);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sendResult;
    }

}

