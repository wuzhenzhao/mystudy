package com.wuzz.demo.concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @description: 类功能描述
 * @author: wuzhenzhao
 * @time 2020/4/26 19:42
 * @since 1.0
 **/
public class ConditionDemoSignal extends Thread {
    private Lock lock;
    private Condition condition;

    public ConditionDemoSignal(Lock lock, Condition condition) {
        this.lock = lock;
        this.condition = condition;
    }

    @Override
    public void run() {
        System.out.println("begin -ConditionDemoSignal");
        try {
            lock.lock();
            condition.signal();
            System.out.println("end - ConditionDemoSignal");
        } finally {
            lock.unlock();
        }
    }
}
