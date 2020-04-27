package com.wuzz.demo.concurrent;

/**
 * @description: VolatileAtomicDemo
 * @author: wuzhenzhao
 * @time 2020/4/26 15:07
 * @since 1.0
 **/
public class VolatileAtomicDemo {
    private static volatile int count =0;

    public static void  inc() {
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        count ++;
    }
    public static void main(String[] args) throws InterruptedException {
        for(int i=0;i<1000;i++) {
            new Thread(VolatileAtomicDemo::inc).start();
        }
        System.out.println("运行结果:"+count);
    }
}
