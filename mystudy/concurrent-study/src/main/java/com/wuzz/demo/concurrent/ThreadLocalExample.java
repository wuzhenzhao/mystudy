package com.wuzz.demo.concurrent;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @description: SimpleDateFormat报错  线程 ThreadLocal
 * @author: wuzhenzhao
 * @time 2021/6/30 10:31
 * @since 1.0
 **/
public class ThreadLocalExample {

    private static final SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static ThreadLocal<SimpleDateFormat> local = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
    };

    public static /*synchronized*/ Date parse(String strData) throws ParseException {
        return sdf.parse(strData);
    }
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for(int i=0;i<20;i++){
            executorService.execute(()->{
                try {
                    local.set(new SimpleDateFormat());
                    //用全局的变量，会存在线程安全问题，抛出异常
//                    System.out.println(parse("2021-05-30 20:12:20"));

                    //基于 ThreadLocal  线程独立的 SimpleDateFormat
                    System.out.println(local.get().parse("2021-05-30 20:12:20"));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
