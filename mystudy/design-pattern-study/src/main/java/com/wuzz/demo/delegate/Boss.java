package com.wuzz.demo.delegate;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/3/22
 * Time: 10:43
 * Description 描述:委派模式。，在spring中以 Delegate与 Dispatcher结尾的，都是委派模式。
 * 其实委派模式就是代理模式跟策略模式的一种特殊的组合情况。
 * https://www.cnblogs.com/wuzhenzhao/p/10304939.html
 */
public class Boss {

    public static void main(String[] args) {

        //客户请求（Boss）、委派者（Leader）、被委派者（Target）
        //委派者要持有被委派者的引用
        //代理模式注重的是过程， 委派模式注重的是结果
        //策略模式注重是可扩展（外部扩展），委派模式注重内部的灵活和复用
        //委派的核心：就是分发、调度、派遣
        //委派模式：就是静态代理和策略模式一种特殊的组合
        new Leader().doing("登录");
    }
}
