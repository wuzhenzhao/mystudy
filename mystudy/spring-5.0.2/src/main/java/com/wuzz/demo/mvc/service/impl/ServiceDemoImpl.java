package com.wuzz.demo.mvc.service.impl;

import com.wuzz.demo.mvc.annotation.WuzzService;
import com.wuzz.demo.mvc.service.ServiceDemo;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/5/5
 * Time: 19:15
 * Description 描述:
 */
@WuzzService
public class ServiceDemoImpl implements ServiceDemo {

    @Override
    public String get(String name) {
        return "service demo" + name;
    }
}
