package com.wuzz.demo.concurrent.juc.base;

import java.util.concurrent.TimeUnit;

/**
 * @description: 类功能描述
 * @author: wuzhenzhao
 * @time 2020/4/26 14:27
 * @since 1.0
 **/
public class InterruptDemo {
//    private static int i;

    //    public static void main(String[] args) throws InterruptedException {
//        Thread thread = new Thread(() -> {
//            while (!Thread.currentThread().isInterrupted()) {
//                i++;
//            }
//            System.out.println("Num:" + i);
//        }, "interruptDemo");
//        thread.start();
//        TimeUnit.SECONDS.sleep(1);
//        thread.interrupt();
//    }
//    public static void main(String[] args) throws InterruptedException {
//        Thread thread = new Thread(() -> {
//            while (true) {
//                boolean ii = Thread.currentThread().isInterrupted();
//                if (ii) {
//                    System.out.println("before:" + ii);
//                    Thread.interrupted();//对线程进行复位，复位标识为 false
//                    System.out.println("after:" + Thread.currentThread().isInterrupted());
//                }
//            }
//        });
//        thread.start();
//        TimeUnit.SECONDS.sleep(1);
//        thread.interrupt();//设置中断标识,中断标识为 true
//    }
    //处于阻塞状态下的线程中断
    private static int i;

    //    public static void main(String[] args) throws InterruptedException {
//        Thread thread=new Thread(()->{
//            while(!Thread.currentThread().isInterrupted()){
//                try {
//                    TimeUnit.SECONDS.sleep(1);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//            System.out.println("Num:"+i);
//        },"interruptDemo");
//        thread.start();
//        TimeUnit.SECONDS.sleep(1);
//        thread.interrupt();
//    }
//    从这个例子中反馈出一个问题，我们平时在线程中使用的sleep、wait、join等操作，它都会抛出一个
//    InterruptedException异常，为什么会抛出异常，是因为它在阻塞期间，必须要能够响应被其他线程发
//    起中断请求之后的一个响应，而这个响应是通过InterruptedException来体现的。
//    但是这里需要注意的是，在这个异常中如果不做任何处理的话，我们是无法去中断线程的，因为当前的
//    异常只是响应了外部对于这个线程的中断命令，同时，线程的中断状态也会复位，如果需要中断，则还
//            需要在catch中添加下面的代码
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt(); //再次中断
                }
            }
            System.out.println("Num:" + i);
        }, "interruptDemo");
        thread.start();
        TimeUnit.SECONDS.sleep(1);
        thread.interrupt();
        System.out.println(thread.isInterrupted());
    }

}
