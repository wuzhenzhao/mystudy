package com.wuzz.demo.concurrent.vol;

/**
 * @description: 类功能描述
 * @author: wuzhenzhao
 * @time 2021/6/24 13:55
 * @since 1.0
 **/
public class VolatileDemo {
    public static boolean stop=false;
    public static void main(String[] args) throws InterruptedException {
        Thread t1=new Thread(()->{
            int i=0;
            while(!stop){
                i++;
            }
        });
        t1.start();
        System.out.println("begin start thread");
        Thread.sleep(1000);
        stop=true;
    }
}

