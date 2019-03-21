package com.wuzz.demo.prototype;

import java.util.ArrayList;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/3/21
 * Time: 11:20
 * Description 描述: 原型模式
 */
public class Prototype implements Cloneable {

    public String name;

    public ArrayList<String> list = new ArrayList();

    /* @Author Wuzhenzhao
    * @Param []
    * @return java.lang.Object
    * @Date 11:38 2019/3/21
    * @Description 浅拷贝
    **/
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
