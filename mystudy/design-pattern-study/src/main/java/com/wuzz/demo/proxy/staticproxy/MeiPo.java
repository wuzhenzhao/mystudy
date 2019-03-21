package com.wuzz.demo.proxy.staticproxy;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/3/21
 * Time: 14:34
 * Description 描述:
 */
public class MeiPo {

    private Person person;

    //没办法扩展
    public MeiPo(Person person){
        this.person = person;
    }

    //目标对象的引用给拿到
    public void findLove(){
        System.out.println("根据你的要求物色");
        this.person.findLove();
        System.out.println("匹配成功准备结婚");
    }
}
