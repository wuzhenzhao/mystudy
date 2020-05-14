package com.wuzz.demo.demo;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/9/9
 * Time: 16:39
 * Description 描述:
 */
public class Test {

    public static void main(String[] args) {
        initFlowRules(); //初始化一个规则
        while (true) {
            Entry entry = null;
            try {
                entry = SphU.entry("HelloWorld");
                /*您的业务逻辑 - 开始*/
                System.out.println("hello world");
                /*您的业务逻辑 - 结束*/
            } catch (BlockException e1) {
                /*流控逻辑处理 - 开始*/
                System.out.println("block!");
                /*流控逻辑处理 - 结束*/
            } finally {
                if (entry != null) {
                    entry.exit();
                }
            }
        }
    }

    //初始化规则
    private static void initFlowRules() {
        List<FlowRule> rules = new ArrayList<>(); //限流规则的集合
        FlowRule flowRule = new FlowRule();//限流规则
        flowRule.setResource("HelloWorld");//资源(可以是方法名称、接口）
        //线程数(FLOW_GRADE_THREAD)与QPS (FLOW_GRADE_QPS)
        flowRule.setGrade(RuleConstant.FLOW_GRADE_QPS); //限流的阈值的类型
        flowRule.setCount(20);// QPS数
        rules.add(flowRule);
        FlowRuleManager.loadRules(rules);
    }
//public static void main(String[] args) {
//    Student s=new Student("wuzz",15);
//    Student next=new Student("yyl",2);
//    Student  s2=s;
//    s2.setNext(next);
//    s2=next;
//    System.out.println(s.next.age);
//    System.out.println(s2.age);
//}
//static class Student{
//    public String name;
//    public int age;
//    public Student next;
//
//    public Student getNext() {
//        return next;
//    }
//
//    public void setNext(Student next) {
//        this.next = next;
//    }
//
//    public Student(String name, int age) {
//        this.name = name;
//        this.age = age;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public int getAge() {
//        return age;
//    }
//
//    public void setAge(int age) {
//        this.age = age;
//    }
//}
//public static void main(String[] args) {
//    int a=1;
//    switch (a) {
//        case 1:
//            break;
//        default:
//            break;
//    }
//}
}
