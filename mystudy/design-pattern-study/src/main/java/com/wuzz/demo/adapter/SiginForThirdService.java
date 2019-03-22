package com.wuzz.demo.adapter;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/3/22
 * Time: 11:13
 * Description 描述:适配器模式（Adapter Pattern）是作为两个不兼容的接口之间的桥梁。
 * 这种类型的设计模式属于结构型模式，它结合了两个独立接口的功能。
 * 这种模式涉及到一个单一的类，该类负责加入独立的或不兼容的接口功能。
 * 可以让任何两个没有关联的类一起运行。 2、提高了类的复用。 3、增加了类的透明度。 4、灵活性好。
 */
public class SiginForThirdService extends SiginService {

    public ResultMsg loginForQQ(String openId){
        //1、openId是全局唯一，我们可以把它当做是一个用户名(加长)
        //2、密码默认为是一个常量QQ_EMPTY
        //3、注册（在原有系统里面创建一个用户）
        //4、调用原来的登录方法

        return loginForRegist(openId,null);
    }

    public ResultMsg loginForWechat(String openId){
        return null;
    }

    public ResultMsg loginForTelphone(String telphone,String code){

        return null;
    }
    // 这里封装了原来的注册跟登陆，想要用一个陌生的QQ来登陆，首先当然是注册
    // 注册完调用一下登陆不久OK了吗
    public ResultMsg loginForRegist(String username,String password){
        super.regist(username,null);
        return super.login(username,null);
    }
}
