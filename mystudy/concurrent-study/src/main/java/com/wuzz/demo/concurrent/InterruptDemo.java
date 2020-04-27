package com.wuzz.demo.concurrent;

import java.util.concurrent.TimeUnit;

/**
 * @description: 类功能描述
 * @author: wuzhenzhao
 * @time 2020/4/26 14:27
 * @since 1.0
 **/
public class InterruptDemo {
    private static int i;

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
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            while (true) {
                boolean ii = Thread.currentThread().isInterrupted();
                if (ii) {
                    System.out.println("before:" + ii);
                    Thread.interrupted();//对线程进行复位，复位标识为 false
                    System.out.println("after:" + Thread.currentThread().isInterrupted());
                }
            }
        });
        thread.start();
        TimeUnit.SECONDS.sleep(1);
        thread.interrupt();//设置中断标识,中断标识为 true
    }
}
