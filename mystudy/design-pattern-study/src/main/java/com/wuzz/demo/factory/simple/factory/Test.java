package com.wuzz.demo.factory.simple.factory;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/3/18
 * Time: 18:15
 * Description:
 */
public class Test {
    public static void main(String[] args) {
        SimpleFactory simpleFactory = new SimpleFactory();
        //输出 特仑苏牛奶
        System.out.println(simpleFactory.getMilk("特仑苏").getName());

    }
}
