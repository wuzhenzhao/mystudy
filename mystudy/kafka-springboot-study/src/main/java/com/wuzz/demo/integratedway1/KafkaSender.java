package com.wuzz.demo.integratedway1;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

/**
 * User: Wuzhenzhao
 * Date: 2019/12/9
 * Time: 19:38
 * Description:
 * ClassPath:com.wuzz.demo.integratedway1.KafkaSender
 */
@Component
public class KafkaSender {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private Gson gson = new GsonBuilder().create();

    //发送消息方法
    public void send() {
        Message message = new Message();
        message.setId(System.currentTimeMillis());
        message.setMsg(UUID.randomUUID().toString());
        message.setSendTime(new Date());
        String topics = System.getProperty("topics");
        System.out.println("+++++++++++++++++++++  message = {}"+gson.toJson(message));
        kafkaTemplate.send(topics, gson.toJson(message));
    }
}
