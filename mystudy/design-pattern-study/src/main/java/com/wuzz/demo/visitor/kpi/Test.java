package com.wuzz.demo.visitor.kpi;

/**
 * @description:
 * @author: Wuzhenzhao
 * @time 2020/3/23 20:19
 * @since 1.0
 **/
public class Test {
    public static void main(String[] args) {
        BusinessReport report = new BusinessReport();
        System.out.println("==========CEO看报表===============");
        report.showReport(new CEOVistitor());
        System.out.println("==========CTO看报表===============");
        report.showReport(new CTOVistitor());
    }
}
