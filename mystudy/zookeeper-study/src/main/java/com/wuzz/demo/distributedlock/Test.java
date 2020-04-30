package com.wuzz.demo.distributedlock;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * @description: 类功能描述
 * @author: wuzhenzhao
 * @time 2020/4/30 10:43
 * @since 1.0
 **/
public class Test {
    public static void main( String[] args ) throws IOException {
        //模拟10个线程同时抢占
        CountDownLatch countDownLatch=new CountDownLatch(10);
        for(int i=0;i<10;i++){
            new Thread(()->{
                try {
                    countDownLatch.await();
                    //这一步做的只是创建链接 + 根节点
                    DistributedLock distributedLock=new DistributedLock();
                    distributedLock.lock(); //获得锁
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            },"Thread-"+i).start();
            countDownLatch.countDown();
        }
        System.in.read();
    }

}
