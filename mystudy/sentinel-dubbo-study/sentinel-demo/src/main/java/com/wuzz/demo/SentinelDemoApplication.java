package com.wuzz.demo;

import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/5/5
 * Time: 16:51
 * Description 描述:
 */
@SpringBootApplication
public class SentinelDemoApplication {
    public static void main(String[] args) throws IOException {
        initFlowRules();
        SpringApplication.run(SentinelDemoApplication.class, args);
    }

    //初始化规则
    private static void initFlowRules(){
        List<FlowRule> rules=new ArrayList<>(); //限流规则的集合
        FlowRule flowRule=new FlowRule();
        flowRule.setResource("sayHello");//资源(方法名称、接口）
        flowRule.setGrade(RuleConstant.FLOW_GRADE_QPS); //限流的阈值的类型
        flowRule.setCount(2);
        rules.add(flowRule);
        FlowRuleManager.loadRules(rules);
    }
}


