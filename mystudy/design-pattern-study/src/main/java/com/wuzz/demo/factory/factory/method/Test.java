package com.wuzz.demo.factory.factory.method;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/3/19
 * Time: 10:49
 * Description:
 */
public class Test {

    public static void main(String[] args) {
        MengNiuFactory mengNiuFactory = new MengNiuFactory();
        //输出 蒙牛牛奶
        System.out.println(mengNiuFactory.getMilk().getName());
    }
}
