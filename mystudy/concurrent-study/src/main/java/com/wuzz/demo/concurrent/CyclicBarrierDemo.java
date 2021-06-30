package com.wuzz.demo.concurrent;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @description: 类功能描述
 * @author: wuzhenzhao
 * @time 2021/6/29 18:49
 * @since 1.0
 **/
public class CyclicBarrierDemo {

    public static void main(String[] args) {
        int n=4;
        CyclicBarrier cyclicBarrier=new CyclicBarrier(4,()->{
            System.out.println("所有写入数据完毕，等待其他线程写入。");
        });
        for(int i=0;i<n;i++){
            new Writer(cyclicBarrier).start();
        }


    }

    static class Writer extends Thread{
        private CyclicBarrier cyclicBarrier;
        public Writer(CyclicBarrier cyclicBarrier) {
            this.cyclicBarrier=cyclicBarrier;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(new Random().nextInt(2000));
                System.out.println(Thread.currentThread().getName()+"写入数据完毕，等待其他线程写入。");
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
//            System.out.println("所有写入数据完毕，等待其他线程写入。");
        }
    }
}
