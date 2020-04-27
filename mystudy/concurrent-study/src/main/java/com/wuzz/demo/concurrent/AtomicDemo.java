package com.wuzz.demo.concurrent;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @description: 原子操作demo
 * @author: wuzhenzhao
 * @time 2020/4/27 10:55
 * @since 1.0
 **/
public class AtomicDemo {
    private static AtomicInteger count = new AtomicInteger(0);

    public static /*synchronized*/ void inc() {
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        count.getAndIncrement();
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 1000; i++) {
            new Thread(() -> {
                AtomicDemo.inc();
            }).start();
        }
        Thread.sleep(4000);
        System.out.println(count.get());
    }
}
