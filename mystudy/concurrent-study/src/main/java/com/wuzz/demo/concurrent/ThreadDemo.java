package com.wuzz.demo.concurrent;

/**
 * @description: ThreadDemo
 * @author: wuzhenzhao
 * @time 2020/4/26 10:59
 * @since 1.0
 **/
public class ThreadDemo extends Thread {

    public ThreadDemo(String name) {
        super(name);
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }


    public static void main(String[] args) {
        ThreadDemo t1 = new ThreadDemo("t1=====>");
        ThreadDemo t2 = new ThreadDemo("t2=====>");
        t1.start();
        t2.start();
    }
}
