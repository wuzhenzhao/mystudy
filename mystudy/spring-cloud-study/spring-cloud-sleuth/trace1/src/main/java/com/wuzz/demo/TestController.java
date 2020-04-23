package com.wuzz.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.logging.Logger;

/**
 * @description:
 * @author: Wuzhenzhao@hikvision.com.cn
 * @time 2020/4/23 14:27
 * @since 1.0
 **/
@RestController
public class TestController {
    private final Logger logger = Logger.getLogger(getClass().getName());

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value = "/trace-1", method = RequestMethod.GET)
    public String trace() {
        logger.info(" === call trace- 1 === ");
        return restTemplate.getForEntity("http://trace-2/trace-2", String.class).getBody();
    }
}
