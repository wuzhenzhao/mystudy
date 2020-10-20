package com.wuzz.demo.visitor.kpi;

/**
 * @description:
 * @author: Wuzhenzhao
 * @time 2020/3/23 19:57
 * @since 1.0
 **/
public interface IVisitor {

    //访问工程师类型
    void visit(Engineer engineer);

    //访问经理类型
    void visit(Manager manager);
}
