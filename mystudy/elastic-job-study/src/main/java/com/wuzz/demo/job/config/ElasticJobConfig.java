package com.wuzz.demo.job.config;

import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.dangdang.ddframe.job.config.JobCoreConfiguration;
import com.dangdang.ddframe.job.config.simple.SimpleJobConfiguration;
import com.dangdang.ddframe.job.lite.api.JobScheduler;
import com.dangdang.ddframe.job.lite.api.strategy.impl.AverageAllocationJobShardingStrategy;
import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration;
import com.dangdang.ddframe.job.lite.spring.api.SpringJobScheduler;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import com.wuzz.demo.job.SimpleJobDemo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description: 类功能描述
 * @author: wuzhenzhao
 * @time 2020/7/14 14:57
 * @since 1.0
 **/
@Configuration
public class ElasticJobConfig {

    //注册中心
    @Autowired
    private ZookeeperRegistryCenter regCenter;

    @Bean(initMethod = "init")
    public JobScheduler simpleJobScheduler(final SimpleJobDemo simpleJob,
                                           @Value("${wuzzJob.cron}") final String cron,
                                           @Value("${wuzzJob.shardingTotalCount}") final int shardingTotalCount,
                                           @Value("${wuzzJob.shardingItemParameters}") final String
                                                   shardingItemParameters) {
        //参数依次是 自定义任务类型实现类、cron表达式、分片数、分片参数
        LiteJobConfiguration liteJobConfiguration =
                getLiteJobConfiguration(simpleJob.getClass(), cron, shardingTotalCount, shardingItemParameters);

        return new SpringJobScheduler(simpleJob, regCenter, liteJobConfiguration);
    }

    /**
     * 功能描述: <br>
     * LiteJobConfiguration 配置
     * @Param: [jobClass, cron, shardingTotalCount, shardingItemParameters]
     * @Return: com.dangdang.ddframe.job.lite.config.LiteJobConfiguration
     * @Author: wuzhenzhao
     * @Date: 2020/7/14 17:03
     */
    private LiteJobConfiguration getLiteJobConfiguration(final Class<? extends SimpleJob> jobClass,
                                                         final String cron,
                                                         final int shardingTotalCount,
                                                         final String shardingItemParameters) {
        JobCoreConfiguration coreConfig = JobCoreConfiguration
                //任务名称、cron表达式、分片数
                .newBuilder(jobClass.getName(), cron, shardingTotalCount)
                .shardingItemParameters(shardingItemParameters).build();

        // 作业分片策略
        // 基于平均分配算法的分片策略
        String jobShardingStrategyClass = AverageAllocationJobShardingStrategy.class.getCanonicalName();

        return LiteJobConfiguration.newBuilder(
                new SimpleJobConfiguration(coreConfig, jobClass.getCanonicalName()))
                .jobShardingStrategyClass(jobShardingStrategyClass)
                //允许重写配置
                .overwrite(true).build();
    }
}
