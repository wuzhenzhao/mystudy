package com.wuzz.demo.integratedway1.controller;

import com.wuzz.demo.integratedway1.MqProducer;
import com.wuzz.demo.integratedway1.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
    private MqProducer mqProducer;

    @RequestMapping(value = "/testStringQueue.json", method = {RequestMethod.GET})
    public void testStringQueue() {

        for (int i = 1; i <= 1; i++) {
            System.out.println("第" + i + "次发送字符串队列消息");
            mqProducer.sendStringQueue("stringQueue", "消息：" + i);
        }
    }


//    @RequestMapping(value = "/testStringListQueue.json", method = {RequestMethod.GET})
//    public void testStringListQueue() {
//
//        List<String> idList = new ArrayList<>();
//        idList.add("id1");
//        idList.add("id2");
//        idList.add("id3");
//
//        System.out.println("正在发送集合队列消息ing......");
//        mqProducer.sendStringListQueue("stringListQueue", idList);
//    }
//
//
//    @RequestMapping(value = "/testObjQueue.json", method = {RequestMethod.GET})
//    public void testObjQueue() {
//
//        System.out.println("正在发送对象队列消息......");
//        mqProducer.sendObjQueue("objQueue", new User("1", "小明", 20));
//    }
//
//
//    @RequestMapping(value = "/testObjListQueue.json", method = {RequestMethod.GET})
//    public void testObjListQueue() {
//
//        System.out.println("正在发送对象集合队列消息......");
//
//        List<Serializable> userList = new ArrayList<>();
//        userList.add(new User("1", "小明", 21));
//        userList.add(new User("2", "小雪", 22));
//        userList.add(new User("3", "小花", 23));
//
//        mqProducer.sendObjListQueue("objListQueue", userList);
//    }


    @RequestMapping(value = "/testStringTopic.json", method = {RequestMethod.GET})
    public void testStringTopic() {

        for (int i = 1; i <= 100; i++) {
            System.out.println("第" + i + "次发送字符串主题消息");
            mqProducer.sendStringTopic("stringTopic", "消息：" + i);
        }
    }


//    @RequestMapping(value = "/testStringListTopic.json", method = {RequestMethod.GET})
//    public void testStringListTopic() {
//
//        List<String> idList = new ArrayList<>();
//        idList.add("id1");
//        idList.add("id2");
//        idList.add("id3");
//
//        System.out.println("正在发送集合主题消息ing......");
//        mqProducer.sendStringListTopic("stringListTopic", idList);
//    }
//
//
//    @RequestMapping(value = "/testObjTopic.json", method = {RequestMethod.GET})
//    public void testObjTopic() {
//
//        System.out.println("正在发送对象主题消息......");
//        mqProducer.sendObjTopic("objTopic", new User("1", "小明", 20));
//    }
//
//
//    @RequestMapping(value = "/testObjListTopic.json", method = {RequestMethod.GET})
//    public void testObjListTopic() {
//
//        System.out.println("正在发送对象集合主题消息......");
//
//        List<Serializable> userList = new ArrayList<>();
//        userList.add(new User("1", "小明", 21));
//        userList.add(new User("2", "小雪", 22));
//        userList.add(new User("3", "小花", 23));
//
//        mqProducer.sendObjListTopic("objListTopic", userList);
//    }
}

