package com.wuzz.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: Wuzhenzhao@hikvision.com.cn
 * @time 2020/4/23 14:27
 * @since 1.0
 **/
@RestController
public class TestController {
    private final static Logger log = LoggerFactory.getLogger(TestController.class);

    @RequestMapping(value = "/trace-2", method = RequestMethod.GET)
    public String trace() {
        log.info(" = = = <call trace-2> == = ");
        return " Return ===call trace-2 === ";
    }
}
