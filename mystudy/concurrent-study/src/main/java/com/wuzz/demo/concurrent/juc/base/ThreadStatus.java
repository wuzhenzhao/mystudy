package com.wuzz.demo.concurrent.juc.base;

import java.util.concurrent.TimeUnit;

/**
 * @description: 类功能描述
 * @author: wuzhenzhao
 * @time 2021/6/18 15:54
 * @since 1.0
 **/
public class ThreadStatus {
    public static void main(String[] args) {
        //TIME_WAITING
        new Thread(() -> {
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "timewaiting").start();
        //WAITING，线程在ThreadStatus类锁上通过wait进行等待
        new Thread(() -> {
            while (true) {
                synchronized (ThreadStatus.class) {
                    try {
                        ThreadStatus.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "Waiting").start();
        //线程在ThreadStatus加锁后，不会释放锁
        new Thread(new BlockedDemo(), "BlockDemo-01").start();
        new Thread(new BlockedDemo(), "BlockDemo-02").start();
    }

    static class BlockedDemo extends Thread {
        public void run() {
            synchronized (BlockedDemo.class) {
                while (true) {
                    try {
                        TimeUnit.SECONDS.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
//    运行上述示例，打开终端命令，输入"jps"（显示当前所有Java进程pid）；
//    根据获取到的pid， 通过jstack pid ，可以打印指定Java进程ID的堆栈信息
//    通过堆栈信息，可以看到线程的运行状态

}
