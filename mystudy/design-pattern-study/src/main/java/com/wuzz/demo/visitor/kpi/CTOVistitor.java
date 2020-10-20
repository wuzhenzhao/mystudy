package com.wuzz.demo.visitor.kpi;

/**
 * @description:
 * @author: Wuzhenzhao
 * @time 2020/3/23 20:19
 * @since 1.0
 **/
public class CTOVistitor implements IVisitor {
    public void visit(Engineer engineer) {
        System.out.println("工程师" +  engineer.name + "，代码行数：" + engineer.getCodeLines());
    }

    public void visit(Manager manager) {
        System.out.println("经理：" +  manager.name + "，产品数量：" + manager.getProducts());
    }
}
