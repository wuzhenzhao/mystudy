package com.wuzz.demo;

import com.alibaba.csp.sentinel.init.InitFunc;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRuleManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/9/9
 * Time: 18:11
 * Description 描述:
 */
public class DataSourceInitFunc implements InitFunc {
    @Override
    public void init() throws Exception {
        List<DegradeRule> rules = new ArrayList<DegradeRule>();
        DegradeRule rule = new DegradeRule();
        //下面这个配置的意思是，当1s内持续进入5个请求，平均响应时间都超过count(10ms),
        // 那么在接下来的timewindow(10s)内，对
        //这个方法的调用都会自动熔断，抛出异常:degradeException.
        //指定被保护的资源
        rule.setResource("com.wuzz.demo.SentinelService");
        //降级模式, RT（平均响应时间）、异常比例(DEGRADE_GRADE_EXCEPTION_RATIO)/异常数量
        //1s内处理5个请求
        rule.setGrade(RuleConstant.DEGRADE_GRADE_RT);
        rule.setCount(100); //阈值 5个请求平均响应时间超过100ms  触发降级
        rule.setTimeWindow(10);//降级的时间单位, 单位为s
        rules.add(rule);
        DegradeRuleManager.loadRules(rules);
    }
}
