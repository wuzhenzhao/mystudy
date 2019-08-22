package com.wuzz.demo;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/8/16
 * Time: 18:24
 * Description 描述:
 */
public class ReentrantLockTest {

    private static int count = 0;
    static Lock lock = new ReentrantLock();

    public static void inc() {
        lock.lock();
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        count++;
        lock.unlock();
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 1000; i++) {
            new Thread(() -> {
                ReentrantLockTest.inc();
            }).start();
            ;
        }
        Thread.sleep(3000);
        System.out.println("result:" + count);
    }
}
