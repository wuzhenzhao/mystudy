package com.wuzz.demo.concurrent.container;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @description: 类功能描述
 * @author: wuzhenzhao
 * @time 2020/8/26 19:35
 * @since 1.0
 **/
public class ConcurrentLinkedQueueTest {

    public static void main(String[] args) {
        Queue<String> strs = new ConcurrentLinkedQueue<>();//非阻塞队列，链表实现
        Queue<String> strs2 = new ConcurrentLinkedDeque<>();//非阻塞双端队列，链表实现，无界队列

        for(int i=0;i<10;i++) {
            // 根据返回值来判断是否添加成功
            strs.offer("a"+i);
        }

        System.out.println(strs);//[a0, a1, a2, a3, a4, a5, a6, a7, a8, a9]
        System.out.println(strs.size());// 10
        System.out.println(strs.poll());// a0
        System.out.println(strs.size());// 9
        System.out.println(strs.peek());// a1
        System.out.println(strs.size());// 9
    }
}
