package com.wuzz.demo.state;

/**
 * @description:
 * @author: Wuzhenzhao@hikvision.com.cn
 * @time 2020/3/25 11:32
 * @since 1.0
 **/
public class UnLoginState extends UserState {

    @Override
    public void favorite() {
        this.switch2login();
        this.context.getState().favorite();
    }

    @Override
    public void comment(String comment) {
        this.switch2login();
        this.context.getState().comment(comment);
    }

    private void switch2login(){
        System.out.println("跳转到登录页！");
        this.context.setState(this.context.STATE_LOGIN);
    }
}

