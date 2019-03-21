package com.wuzz.demo.prototype;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/3/21
 * Time: 11:36
 * Description 描述:
 */
public class Test {

    public static void main(String[] args) throws CloneNotSupportedException {
//        Prototype p = new Prototype();
        DeepClone p = new DeepClone();
        p.name = "helloworld";
        p.list.add("1");
//        Prototype clone = (Prototype)p.clone();
        DeepClone clone = (DeepClone)p.deepClone();
        p.list.add("2");
        System.out.println(p.list==clone.list);
        System.out.println(clone.list.size());
    }
}
