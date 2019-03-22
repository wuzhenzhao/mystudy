package com.wuzz.demo.delegate;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/3/21
 * Time: 17:57
 * Description 描述:
 */
public interface ITarget {

    //具体需要谁去干，由子类(项目经理)去决定
    // 需要怎么干，由员工定义
    void doing(String command);
}
