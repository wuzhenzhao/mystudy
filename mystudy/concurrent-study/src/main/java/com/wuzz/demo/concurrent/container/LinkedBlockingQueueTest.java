package com.wuzz.demo.concurrent.container;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @description: 阻塞队列，链表实现，无界
 * @author: wuzhenzhao
 * @time 2020/8/26 17:03
 * @since 1.0
 **/
public class LinkedBlockingQueueTest {

    // 阻塞队列
    static BlockingQueue<String> strs = new LinkedBlockingQueue<>();

    static Random r =new Random();
    //生产者消费者
    public static void main(String[] args) {
        new Thread(()->{
            for(int i=0;i<100;i++) {
                try {
                    strs.put("a"+i);//如果满了就会等待阻塞
                    System.err.println("a"+i);
                    TimeUnit.MILLISECONDS.sleep(r.nextInt(1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"p1").start();

        for(int i=0;i<5;i++) {
            new Thread(()->{
                while(true) {
                    try {
                        //take 如果空了 就会阻塞
                        System.out.println(Thread.currentThread().getName()+" take - "+ strs.take());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            },"c" +i).start();
        }
    }
}
