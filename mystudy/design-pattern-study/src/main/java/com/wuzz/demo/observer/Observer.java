package com.wuzz.demo.observer;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/3/22
 * Time: 12:16
 * Description 描述:
 */
public class Observer {

    public void advice(Event e){
        System.out.println("===触发"+e.getTrigger()+"事件，打印日志===\n" + e);
    }
}
