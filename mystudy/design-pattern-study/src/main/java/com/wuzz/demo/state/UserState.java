package com.wuzz.demo.state;

/**
 * @description:
 * @author: Wuzhenzhao
 * @time 2020/3/25 11:31
 * @since 1.0
 **/
public abstract class UserState {
    protected AppContext context;

    public void setContext(AppContext context) {
        this.context = context;
    }

    public abstract void favorite();

    public abstract void comment(String comment);
}
