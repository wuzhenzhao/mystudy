package com.wuzz.demo.concurrent.container;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @description: 阻塞队列，数组实现。有界阻塞队列
 * @author: wuzhenzhao
 * @time 2020/8/26 16:59
 * @since 1.0
 **/
public class ArrayBlockingQueueTest {

    // 阻塞队列
    static BlockingQueue<String> strs = new ArrayBlockingQueue<>(10);

    public static void main(String[] args) throws InterruptedException {
        for(int i=0;i<10;i++) {
            strs.put("a"+i);
        }
//      strs.add("aaa");//Exception in thread "main" java.lang.IllegalStateException: Queue full
//      strs.put("aaa"); // 满了会等待 阻塞
//      strs.offer("aaa");//不会报异常 有返回值  true or false
//      strs.offer("aaa",1, TimeUnit.SECONDS); // 按照时间段阻塞
    }
}
