package com.wuzz.demo.customer;

import com.wuzz.demo.customer.SinkSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: Wuzhenzhao@hikvision.com.cn
 * @time 2020/4/22 14:51
 * @since 1.0
 **/
@RestController
public class TestController {

//    @Autowired
//    private SinkSender sinkSender;
////    @Autowired
////    private MessageChannel output;
//    @GetMapping(value = "/hello")
//    public void test(){
////        output.send(MessageBuilder.withPayload("From MessageChannel").build());
//        sinkSender.input().send(MessageBuilder.withPayload("From SinkSender").build());
//    }
}
