package com.wuzz.demo.mvc.controller;

import com.wuzz.demo.mvc.annotation.WuzzAutowired;
import com.wuzz.demo.mvc.annotation.WuzzController;
import com.wuzz.demo.mvc.annotation.WuzzRequestMapping;
import com.wuzz.demo.mvc.service.ServiceDemo;

import java.io.IOException;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/4/26
 * Time: 11:37
 * Description 描述:
 */
@WuzzController
@WuzzRequestMapping("/wuzz")
public class TestController  extends BaseController{

    @WuzzAutowired
    private ServiceDemo serviceDemo;

    @WuzzRequestMapping("/index.do")
    public void index() {
        try {
            response.getWriter().write("index"+serviceDemo.get("wuzz"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @WuzzRequestMapping("/index1.do")
    public void index1() {
        try {
            response.getWriter().write("index1");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @WuzzRequestMapping("/index2.do")
    public void index2() {
        try {
            response.getWriter().write("index2");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @WuzzRequestMapping("/index3.do")
    public void index3() {
        try {
            response.getWriter().write("index3");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
