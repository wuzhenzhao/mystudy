package com.wuzz.demo.concurrent.sync;

/**
 * @description: 类功能描述
 * @author: wuzhenzhao
 * @time 2021/6/19 14:30
 * @since 1.0
 **/
public class Demo {
    int i = 0;
    public void incr(){
        i++;
    }
    public static void main(String[] args) {
        Demo demo = new Demo();
        Thread[] threads=new Thread[2];
        for (int j = 0;j<2;j++) {
            threads[j]=new Thread(() -> { // 创建两个线程
                for (int k=0;k<10000;k++) { // 每个线程跑10000次
                    demo.incr();
                }
            });
            threads[j].start();
        }
        try {
            threads[0].join();
            threads[1].join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(demo.i);
    }
}
