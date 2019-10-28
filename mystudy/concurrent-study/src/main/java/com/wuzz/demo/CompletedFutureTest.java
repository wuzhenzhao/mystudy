package com.wuzz.demo;

import java.util.Date;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/10/18
 * Time: 10:17
 * Description 描述: java 8 异步编程
 */
public class CompletedFutureTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

//        CompletableFuture<String> completableFuture =new CompletableFuture<String>();
          //1.完成操作
//        completableFuture.complete("HelloWorld");
//        String value = completableFuture.get();
//        System.out.println(value);

        //2.异步执行，阻塞操作
//        CompletableFuture<Void> runAsync = CompletableFuture.runAsync(() -> {
//            System.out.println("Hello World");
//        });
//        //阻塞操作
//        runAsync.get();
//        System.out.println("Starting.....");

        //3.异步执行，阻塞操作
//        CompletableFuture<String> supplyAsync = CompletableFuture.supplyAsync(() -> {
//            //获取数据操作  假设来自数据库
//            return String.format("[Thread : %s] Hello World....\n",Thread.currentThread().getName());
//        });
//        String value = supplyAsync.get();
//        System.out.println(value);
//        System.out.println("Starting.....");
        //4.合并操作
        CompletableFuture combinedSupplyAsync = CompletableFuture.supplyAsync(() -> {
            //获取数据操作  假设来自数据库
            return String.format("[Thread : %s] Hello World....\n",Thread.currentThread().getName());
        }).thenApply(value ->{

            return value +"来自数据库";
        }).thenApply(value ->{
            System.out.println(value);
            return value + new Date();
        }).thenRun(()->{
            System.out.println("操作结束");
        });
        while (!combinedSupplyAsync.isDone()){

        }
    }
}
