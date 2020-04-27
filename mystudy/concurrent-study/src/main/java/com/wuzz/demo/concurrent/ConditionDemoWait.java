package com.wuzz.demo.concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @description: 类功能描述
 * @author: wuzhenzhao
 * @time 2020/4/26 19:42
 * @since 1.0
 **/
public class ConditionDemoWait extends Thread {
    private Lock lock;
    private Condition condition;

    public ConditionDemoWait(Lock lock, Condition condition) {
        this.lock = lock;
        this.condition = condition;
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        System.out.println("begin -ConditionDemoWait");
        try {
            lock.lock();
            condition.await();
            System.out.println("end - ConditionDemoWait");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
