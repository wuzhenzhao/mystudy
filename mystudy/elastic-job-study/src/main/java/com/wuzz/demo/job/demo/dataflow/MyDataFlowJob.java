package com.wuzz.demo.job.demo.dataflow;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.dataflow.DataflowJob;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/3/13
 * Time: 18:13
 * Description:
 */
public class MyDataFlowJob implements DataflowJob<String> {
    private boolean flag = false;

    public List<String> fetchData(ShardingContext shardingContext) {
        System.out.println("开始获取数据");
        if (flag) {
            return null;
        }
        return Arrays.asList("qingshan", "jack", "seven");
    }

    public void processData(ShardingContext shardingContext, List<String> data) {
        for (String val : data) {
            // 处理完数据要移除掉，不然就会一直跑,处理可以在上面的方法里执行。这里采用 flag
            System.out.println("开始处理数据：" + val);
        }
        flag = true;
    }
}
