package com.wuzz.demo.decorator;

import com.wuzz.demo.adapter.SiginService;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/3/22
 * Time: 11:38
 * Description 描述:
 */
public class SigginTest {

    public static void main(String[] args) {

        //原来的功能依旧对外开放，依旧保留
        //新的功能同样的也可以使用
        ISigninForThirdService signinForThirdService = new SigninForThirdService(new SiginService());

        signinForThirdService.loginForQQ("xxssdsd");
    }
}
