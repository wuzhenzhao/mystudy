package com.wuzz.demo;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/8/22
 * Time: 19:43
 * Description 描述:
 */
public class Test {

    public static void main(String[] args) {
        int i = Math.abs("KafkaConsumerGroup".hashCode()) % 50;

        System.out.println(i);
    }
}
