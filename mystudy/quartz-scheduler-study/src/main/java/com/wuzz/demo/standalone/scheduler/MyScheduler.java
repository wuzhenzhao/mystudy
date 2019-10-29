package com.wuzz.demo.standalone.scheduler;

import com.wuzz.demo.standalone.job.MyJob1;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/10/28
 * Time: 20:13
 * Description 描述:
 */
public class MyScheduler {

    public static void main(String[] args) throws SchedulerException {

        // JobDetail
        JobDetail jobDetail = JobBuilder.newJob(MyJob1.class)
                .withIdentity("myJob1", "group1")
                .usingJobData("name", "wuzz")
                .usingJobData("moon", 5.21F)
                .build();

        // Trigger
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger1", "group1")
                .startNow()
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInSeconds(2)
                        .repeatForever())
                .build();

        // SchedulerFactory
        SchedulerFactory factory = new StdSchedulerFactory();

        // Scheduler
        Scheduler scheduler = factory.getScheduler();

        // 绑定关系是1：N
        scheduler.scheduleJob(jobDetail, trigger);
        scheduler.start();
    }
}
