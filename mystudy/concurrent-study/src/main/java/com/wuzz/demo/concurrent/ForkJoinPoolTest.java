package com.wuzz.demo.concurrent;

import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

/**
 * @description: 类功能描述
 * @author: wuzhenzhao
 * @time 2020/6/4 20:00
 * @since 1.0
 **/
public class ForkJoinPoolTest {
    static int[] nums= new int[1000000];
    static final int MAX_NUM =50000;
    static Random r=new Random();

    static {
        for(int i=0;i<nums.length;i++) {
            nums[i] = r.nextInt(100);
        }
        System.out.println(Arrays.stream(nums).sum());
    }
    //无返回值
    static class AddTask extends RecursiveAction {
        int start, end;

        private AddTask(int start, int end) {
            this.start = start;
            this.end = end;
        }
        @Override
        protected void compute() {
            if(end -start <MAX_NUM) {
                long sum =0L;
                for(int i=start;i<end ;i++) sum += nums[i];
                System.out.println("from "+start +" to "+end+" = "+sum);
            }else {
                int middle =start +(end-start)/2;
                AddTask task1 = new AddTask(start, middle);
                AddTask task2 = new AddTask(middle, end);
                task1.fork();
                task2.fork();
            }

        }

    }
    //有返回值
    static class AddTask2 extends RecursiveTask<Long> {
        int start, end;

        private AddTask2(int start, int end) {
            this.start = start;
            this.end = end;
        }
        @Override
        protected Long compute() {
            if(end -start <MAX_NUM) {
                long sum =0L;
                for(int i=start;i<end ;i++) sum += nums[i];
                return sum;
            }
            int middle =start +(end-start)/2;
            AddTask2 task1 = new AddTask2(start, middle);
            AddTask2 task2 = new AddTask2(middle, end);
            task1.fork();//启动新线程
            task2.fork();
            return task1.join() + task2.join();

        }

    }
    public static void main(String[] args) throws IOException {
        ForkJoinPool fjp = new ForkJoinPool();

//        AddTask task = new AddTask(0, nums.length);
//        fjp.execute(task);
        AddTask2 task2 = new AddTask2(0, nums.length);
        fjp.execute(task2);
        long result= task2.join();//阻塞的
        System.out.println(result);
        System.in.read();
    }
}
