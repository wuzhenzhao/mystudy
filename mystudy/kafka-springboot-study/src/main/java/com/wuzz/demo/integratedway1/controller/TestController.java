package com.wuzz.demo.integratedway1.controller;

import com.wuzz.demo.integratedway1.KafkaSender;
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
    private KafkaSender kafkaSender;

    @RequestMapping(value = "/testSend.json", method = {RequestMethod.GET})
    public void testSend() {

        kafkaSender.send();
    }

}

