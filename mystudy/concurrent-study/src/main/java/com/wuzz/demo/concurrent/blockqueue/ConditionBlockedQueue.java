package com.wuzz.demo.concurrent.blockqueue;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description: 类功能描述
 * @author: wuzhenzhao
 * @time 2021/6/29 11:22
 * @since 1.0
 **/
public class ConditionBlockedQueue {

    //表示阻塞队列容器
    private List<String> items;

    private volatile int size;

    private volatile int count;

    private Lock lock = new ReentrantLock();
    // take
    private final Condition notEmpty = lock.newCondition();
    // add
    private final Condition notFull = lock.newCondition();

    public ConditionBlockedQueue(int count) {
        this.count = count;
        items = new ArrayList<>(count);
    }

    //阻塞添加
    public void put(String item) throws InterruptedException {

        lock.lock();
        try {
            if (size >= count) {
                System.out.println("队列满了，需要等待。。。。");
                notFull.await();
            }
            ++size;//添加元素个数
            items.add(item);
            notEmpty.signal();
        } finally {
            lock.unlock();
        }

    }

    //阻塞获取
    public String take() throws InterruptedException {

        lock.lock();
        try {
            if (size == 0) {
                System.out.println("阻塞队列空了。等待生产");
                notEmpty.await();
            }
            --size;//添加元素个数
            String item = items.remove(0);
            notFull.signal();
            return item;
        } finally {
            lock.unlock();
        }

    }

    public static void main(String[] args) throws InterruptedException {
        ConditionBlockedQueue queue = new ConditionBlockedQueue(10);
        //生产
        Thread t1 = new Thread(() -> {
            Random random = new Random();
            for (int i = 0; i < 1000; i++) {
                String item = "item-" + i;
                try {
                    queue.put(item);
                    System.out.println("生产一个元素：" + item);
                    Thread.sleep(random.nextInt(1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        Thread.sleep(1000);
        Thread t2 = new Thread(() -> {
            Random random = new Random();
            for (; ; ) {

                try {
                    String item = queue.take();
                    System.out.println("消费一个元素：" + item);
                    Thread.sleep(random.nextInt(1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t2.start();
    }
}
