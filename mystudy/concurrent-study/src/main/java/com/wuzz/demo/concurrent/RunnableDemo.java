package com.wuzz.demo.concurrent;

/**
 * @description: Runnable demo
 * @author: wuzhenzhao
 * @time 2020/4/26 11:09
 * @since 1.0
 **/
public class RunnableDemo implements Runnable {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }


    public static void main(String[] args) {
        Thread thread1 = new Thread(new RunnableDemo(), "t1=====>");
        Thread thread2 = new Thread(new RunnableDemo(), "t2=====>");
        thread1.start();
        thread2.start();
    }
}
