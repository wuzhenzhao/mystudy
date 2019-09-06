package com.wuzz.demo;

import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

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
//@SpringBootApplication
//public class SentinelProviderApplication {
//
//    public static void main(String[] args) throws IOException {
//        initFlowRules();
//        SpringApplication.run(SentinelProviderApplication.class, args);
//        System.in.read();
//    }
//
//    //初始化规则
//    private static void initFlowRules() {
//        List<FlowRule> rules = new ArrayList<>(); //限流规则的集合
//        FlowRule flowRule = new FlowRule();
//        flowRule.setResource("com.wuzz.demo.SentinelService:sayHello(java.lang.String)");//资源(方法名称、接口）
//        flowRule.setCount(10);//限流阈值 qps=10
//        flowRule.setGrade(RuleConstant.FLOW_GRADE_QPS);//限流阈值类型（QPS 或并发线程数）
//        //流量控制手段（直接拒绝、Warm Up、匀速排队）
//        flowRule.setControlBehavior(RuleConstant.CONTROL_BEHAVIOR_DEFAULT);
//        flowRule.setLimitApp("sentinel-web");//流控针对的调用来源，若为 default 则不区分调用来源
//        rules.add(flowRule);
//        FlowRuleManager.loadRules(rules);
//    }
//}

public class SentinelProviderApplication {
    public static void main(String[] args) throws IOException {
        initFlowRules();
        ApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(DubboConfig.class);
        ((AnnotationConfigApplicationContext) applicationContext).start();
        System.in.read();
    }

        //初始化规则
    private static void initFlowRules() {
        List<FlowRule> rules = new ArrayList<>(); //限流规则的集合
        FlowRule flowRule = new FlowRule();
        flowRule.setResource("com.wuzz.demo.SentinelService:sayHello(java.lang.String)");//资源(方法名称、接口）
        flowRule.setCount(10);//限流阈值 qps=10
        flowRule.setGrade(RuleConstant.FLOW_GRADE_QPS);//限流阈值类型（QPS 或并发线程数）
        //流量控制手段（直接拒绝、Warm Up、匀速排队）
        flowRule.setControlBehavior(RuleConstant.CONTROL_BEHAVIOR_DEFAULT);
        flowRule.setLimitApp("sentinel-web");//流控针对的调用来源，若为 default 则不区分调用来源
        rules.add(flowRule);
        FlowRuleManager.loadRules(rules);
    }
}


