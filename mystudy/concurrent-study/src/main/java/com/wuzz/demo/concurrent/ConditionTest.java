package com.wuzz.demo.concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description: 类功能描述
 * @author: wuzhenzhao
 * @time 2020/4/26 19:47
 * @since 1.0
 **/
public class ConditionTest {

    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        ConditionDemoWait waitThread = new ConditionDemoWait(lock, condition);
        waitThread.start();
        ConditionDemoSignal signalThread = new ConditionDemoSignal(lock, condition);
        signalThread.start();

    }

}
