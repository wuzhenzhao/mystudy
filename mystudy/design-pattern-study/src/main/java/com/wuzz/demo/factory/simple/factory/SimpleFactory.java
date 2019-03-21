package com.wuzz.demo.factory.simple.factory;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/3/18
 * Time: 18:04
 * Description:
 */
public class SimpleFactory {

    public Milk getMilk(String name) {
        if ("特仑苏".equals(name)) {
            return new TeLunSuMilk();
        } else if ("蒙牛".equals(name)) {
            return new MengNiuMilk();
        }
        return null;
    }
}
