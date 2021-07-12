package com.wuzz.demo.integratedway1.controller;


import com.wuzz.demo.integratedway1.MyProducer;
import com.wuzz.demo.integratedway1.ProducerMessageServiceImpl;
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
    private MyProducer MyProducer;

    @Autowired
    private ProducerMessageServiceImpl producerMessageService;

    @RequestMapping(value = "/testStringQueue.json", method = {RequestMethod.GET})
    public void testStringQueue() {
//        MyProducer.send();
        producerMessageService.send();
    }

}

