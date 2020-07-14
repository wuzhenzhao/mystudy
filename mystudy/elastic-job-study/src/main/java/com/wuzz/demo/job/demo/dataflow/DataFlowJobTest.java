package com.wuzz.demo.job.demo.dataflow;

import com.dangdang.ddframe.job.config.JobCoreConfiguration;
import com.dangdang.ddframe.job.config.dataflow.DataflowJobConfiguration;
import com.dangdang.ddframe.job.lite.api.JobScheduler;
import com.dangdang.ddframe.job.lite.api.strategy.impl.AverageAllocationJobShardingStrategy;
import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration;
import com.dangdang.ddframe.job.reg.base.CoordinatorRegistryCenter;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperConfiguration;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/3/13
 * Time: 18:13
 * Description:
 */
public class DataFlowJobTest {
    // 如果修改了代码，跑之前清空ZK
    public static void main(String[] args) {
        // ZK注册中心
        CoordinatorRegistryCenter regCenter = new ZookeeperRegistryCenter(
                new ZookeeperConfiguration("192.168.1.101:2181", "ejob-standalone"));
        regCenter.init();

        // 定义作业核心配置
        JobCoreConfiguration dataJobCoreConfig = JobCoreConfiguration
                .newBuilder("MyDataFlowJob", "0/10 * * * * ?", 1)
                .build();
        // 定义DATAFLOW类型配置
        DataflowJobConfiguration dataJobConfig = new DataflowJobConfiguration(
                dataJobCoreConfig, MyDataFlowJob.class.getCanonicalName(), true);

        // 作业分片策略
        // 基于平均分配算法的分片策略
        String jobShardingStrategyClass = AverageAllocationJobShardingStrategy.class.getCanonicalName();

        // 定义Lite作业根配置
        // LiteJobConfiguration dataflowJobRootConfig = LiteJobConfiguration.newBuilder(dataJobConfig).jobShardingStrategyClass(jobShardingStrategyClass).build();
        LiteJobConfiguration dataflowJobRootConfig = LiteJobConfiguration.newBuilder(dataJobConfig)
                .overwrite(true).build();

        // 构建Job
        new JobScheduler(regCenter, dataflowJobRootConfig).init();
        // new JobScheduler(regCenter, dataflowJobRootConfig, jobEventConfig).init();
    }

}
