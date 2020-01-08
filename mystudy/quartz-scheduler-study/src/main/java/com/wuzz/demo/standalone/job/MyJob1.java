package com.wuzz.demo.standalone.job;

import org.quartz.*;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/10/28
 * Time: 20:12
 * Description 描述:
 */
//防止一个jobdetail并发执行任务
//@DisallowConcurrentExecution
public class MyJob1 implements Job {

    public void execute(JobExecutionContext context) throws JobExecutionException {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Date date = new Date();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        JobDataMap dataMap = context.getJobDetail().getJobDataMap();
        System.out.println( " " + sf.format(date) + " 任务1执行了，"+dataMap.get("name")+"=========="+context.getJobDetail().isConcurrentExectionDisallowed());
    }
}
