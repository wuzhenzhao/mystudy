package com.wuzz.demo.concurrent;

import org.openjdk.jol.info.ClassLayout;

/**
 * @description: 类功能描述
 * @author: wuzhenzhao
 * @time 2020/5/29 15:21
 * @since 1.0
 **/
public class ClassLayoutDemo {

    // oop.hpp  源码-XX:-UseCompressedOops  关闭压缩指针
    //打开偏向锁  -XX:+UseBiasedLocking -XX:BiasedLockingStartupDelay=0
    public static void main(String[] args) {
        ClassLayoutDemo classLayoutDemo = new ClassLayoutDemo();
        synchronized (classLayoutDemo) {
            System.out.println(classLayoutDemo.hashCode());
            System.out.println(ClassLayout.parseInstance(classLayoutDemo).toPrintable());
        }
    }
}
