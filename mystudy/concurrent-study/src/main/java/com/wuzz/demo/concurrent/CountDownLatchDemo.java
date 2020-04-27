package com.wuzz.demo.concurrent;

import java.util.concurrent.CountDownLatch;

/**
 * @description: 类功能描述
 * @author: wuzhenzhao
 * @time 2020/4/27 10:40
 * @since 1.0
 **/
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(3);
        System.out.println("计数器为"+countDownLatch.getCount());

        new Thread(() -> {
            countDownLatch.countDown();
            System.out.println(Thread.currentThread().getName() +"执行完毕,计数器为"+countDownLatch.getCount());
        }, "t1").start();
        new Thread(() -> {
            countDownLatch.countDown();
            System.out.println(Thread.currentThread().getName() +"执行完毕,计数器为"+countDownLatch.getCount());
        }, "t2").start();
        new Thread(() -> {
            countDownLatch.countDown();
            System.out.println(Thread.currentThread().getName() +"执行完毕,计数器为"+countDownLatch.getCount());
        }, "t3").start();
        countDownLatch.await();//阻塞
        System.out.println("所有线程执行完毕");
    }
}
