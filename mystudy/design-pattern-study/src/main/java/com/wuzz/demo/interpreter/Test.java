package com.wuzz.demo.interpreter;

/**
 * @description: 解释器模式
 * @author: Wuzhenzhao
 * @time 2020/3/25 11:03
 * @since 1.0
 **/
public class Test {

    public static void main(String[] args) {
        System.out.println("result: " + new Calculator("10 + 30").calculate());
        System.out.println("result: " + new Calculator("10 + 30 - 20").calculate());
        System.out.println("result: " + new Calculator("100 * 2 + 400 * 1 + 66").calculate());
    }

}
