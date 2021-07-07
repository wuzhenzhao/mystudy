package com.wuzz.demo.concurrent;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @description: 类功能描述
 * @author: wuzhenzhao
 * @time 2021/7/6 10:50
 * @since 1.0
 **/
public class FutureDemo {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        //FutureTask 区分 RunnableTask  也是实现了Runnable接口的
//        FutureTask<Integer> task = new FutureTask<>(()-> {
//            try {// 该任务将来会有个返回值是Integer类型
//                TimeUnit.MILLISECONDS.sleep(500);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            return 1000;
//        }); // new Callable()
//
//        new Thread(task).start();
//
//        System.out.println(task.get());//阻塞

        //************************************
//        ExecutorService service = Executors.newFixedThreadPool(5);
//        Future<Integer> f = service.submit(()->{// callable
//            try {
//                TimeUnit.MILLISECONDS.sleep(500);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            return 1;
//        });
//        System.out.println(f.isDone());// 任务执行完没有啊？
//        System.out.println(f.get()); // 阻塞  1
//        System.out.println(f.isDone());
        //************************************
//        supplyAsync表示创建带返回值的异步任务的，相当于ExecutorService submit(Callable<T> task) 方法，
//        runAsync表示创建无返回值的异步任务，相当于ExecutorService submit(Runnable task)方法，这两方法的效果跟submit是一样的，
//        测试用例如下：

        // 创建异步执行任务，有返回值
//        CompletableFuture cf = CompletableFuture.supplyAsync(() -> {
//            System.out.println(Thread.currentThread() + " start,time->" + System.currentTimeMillis());
//
//            System.out.println(Thread.currentThread() + " exit,time->" + System.currentTimeMillis());
//            return 1.2;
//
//        }).thenAccept(rs->{
//            System.out.println("**************"+rs);
//        });
//        System.out.println("main thread start,time->" + System.currentTimeMillis());
//        //等待子任务执行完成
////        System.out.println("run result->" + cf.get());
//        System.out.println("main thread exit,time->" + System.currentTimeMillis());

        //************************************
        // 创建异步执行任务，无返回值
//        CompletableFuture cf = CompletableFuture.runAsync(()->{
//            System.out.println(Thread.currentThread()+" start,time->"+System.currentTimeMillis());
//            try {
//                Thread.sleep(2000);
//            } catch (InterruptedException e) {
//            }
//            if(false){
//                throw new RuntimeException("test");
//            }else{
//                System.out.println(Thread.currentThread()+" exit,time->"+System.currentTimeMillis());
//            }
//        });
//        System.out.println("main thread start,time->"+System.currentTimeMillis());
//        //等待子任务执行完成
//        System.out.println("run result->"+cf.get());
//        System.out.println("main thread exit,time->"+System.currentTimeMillis());


//        CompletableFuture<String> task1 = CompletableFuture.supplyAsync(() -> "hello");
//        CompletableFuture<String> task2 = CompletableFuture.supplyAsync(() -> "world");
//        CompletableFuture<String> cf = task1.thenCombine(task2, (r1, r2) -> {
//            System.out.println(r1 + " " + r2);
//            return r1 + " " + r2;
//        }).thenCompose(rs -> CompletableFuture.supplyAsync(() -> {
//            System.out.println(rs);
//            return rs + " " + "Lolololol";
//        }));

//        CompletableFuture<Object> cf = CompletableFuture.supplyAsync(() -> {
//            throw new RuntimeException("eeeee");
////            return 1;
//        }/*,new ThreadPoolExecutor()*/)
////                .runAfterBoth(CompletableFuture.supplyAsync(() -> "hello"), () -> {
////                    System.out.println("Done");
////                })
////                .handle((r, th) -> {
////                    return th != null ? "前置任务出现异常" : r;
////                })
//                .exceptionally(e -> {
//                    System.out.println(e);
//                    return "前置任务出现异常";
//                });
//        ;
//        System.out.println(cf.get());
//        .get();
//        cf.whenComplete((rs, th) -> {
////            if (th != null) {
////                System.out.println("前置任务出现异常");
////            } else {
////                System.out.println("前置任务正常执行 " + rs);
////            }
////        });


        CompletableFuture<String> cf = CompletableFuture.completedFuture("Base Future")
                .thenApply(r -> "Then Apply:" + r);//UniCompletion

        System.out.println(cf.get());

    }
}
