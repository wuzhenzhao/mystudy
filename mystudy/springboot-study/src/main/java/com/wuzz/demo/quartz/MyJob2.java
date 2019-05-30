package com.wuzz.demo.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/5/14
 * Time: 14:29
 * Description 描述:
 */
public class MyJob2 implements Job {

    public void execute(JobExecutionContext context)
            throws JobExecutionException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        System.out.println("job2 "+sdf.format(new Date()));
    }

}
