package com.wuzz.demo.delegate;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/3/22
 * Time: 10:41
 * Description 描述:被委派者
 */
public class TargetA  implements ITarget{

    public void doing(String command) {
        System.out.println("我是员工A，我现在开始干" + command + "工作");
    }
}
