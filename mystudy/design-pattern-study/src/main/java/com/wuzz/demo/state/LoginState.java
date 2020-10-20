package com.wuzz.demo.state;

/**
 * @description:
 * @author: Wuzhenzhao
 * @time 2020/3/25 11:31
 * @since 1.0
 **/
public class LoginState extends UserState {
    @Override
    public void favorite() {
        System.out.println("收藏成功！");
    }

    @Override
    public void comment(String comment) {
        System.out.println(comment);
    }
}
