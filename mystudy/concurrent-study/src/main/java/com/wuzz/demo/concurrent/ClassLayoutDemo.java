package com.wuzz.demo.concurrent;

import org.openjdk.jol.info.ClassLayout;

/**
 * @description: 类功能描述
 * @author: wuzhenzhao
 * @time 2020/5/29 15:21
 * @since 1.0
 **/
public class ClassLayoutDemo {

//    public static void main(String[] args) {
//        ClassLayoutDemo demo=new ClassLayoutDemo(); //o这个对象，在内存中是如何存储和布局的。
//        System.out.println(ClassLayout.parseInstance(demo).toPrintable());
//    }


    // oop.hpp  源码-XX:-UseCompressedOops  关闭压缩指针
    //打开偏向锁  -XX:+UseBiasedLocking -XX:BiasedLockingStartupDelay=0
    public static void main(String[] args) {
        ClassLayoutDemo classLayoutDemo = new ClassLayoutDemo();
        synchronized (classLayoutDemo) {
            System.out.println(classLayoutDemo.hashCode());
            System.out.println(ClassLayout.parseInstance(classLayoutDemo).toPrintable());
        }
    }
      //  轻量级锁
//    public static void main(String[] args) {
//        ClassLayoutDemo demo=new ClassLayoutDemo(); //o这个对象，在内存中是如何存储和布局的。
//        System.out.println(ClassLayout.parseInstance(demo).toPrintable());
//        synchronized (demo){
//            System.out.println(ClassLayout.parseInstance(demo).toPrintable());
//        }
//    }
      //  重量级锁
//    public static void main(String[] args) {
//        ClassLayoutDemo testDemo = new ClassLayoutDemo();
//        Thread t1 = new Thread(() -> {
//            synchronized (testDemo){
//                System.out.println("t1 lock ing");
//                System.out.println(ClassLayout.parseInstance(testDemo).toPrintable());
//            }
//        });
//        t1.start();
//        synchronized (testDemo){
//            System.out.println("main lock ing");
//            System.out.println(ClassLayout.parseInstance(testDemo).toPrintable());
//        }
//    }

}
