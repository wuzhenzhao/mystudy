package com.wuzz.demo.concurrent;

/**
 * @description: 类功能描述
 * @author: wuzhenzhao
 * @time 2021/6/29 19:18
 * @since 1.0
 **/
public class ThreadLocalDemo {
    //希望每个线程获得到的都是0
    private static int num = 0;

    static ThreadLocal<Integer> local = new ThreadLocal<Integer>() {
        @Override
        protected Integer initialValue() {
            return 0;
        }
    };

    public static void main(String[] args) {
        Thread[] thread = new Thread[5];
        for (int i = 0; i < 5; i++) {
            thread[i] = new Thread(() -> {

                int num = local.get().intValue();
                local.set(num += 5);
                System.out.println(Thread.currentThread().getName() + " " + num);
            });
            System.out.println();

        }
        for (int i = 0; i < 5; i++) {
            thread[i].start();
        }
    }
}
