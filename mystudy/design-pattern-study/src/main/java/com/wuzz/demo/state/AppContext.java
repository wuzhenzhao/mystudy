package com.wuzz.demo.state;

/**
 * @description:
 * @author: Wuzhenzhao
 * @time 2020/3/25 11:32
 * @since 1.0
 **/
public class AppContext {

    public static final UserState STATE_LOGIN = new LoginState();
    public static final UserState STATE_UNLOGIN = new UnLoginState();

    private UserState currentState = STATE_LOGIN;

    {
        STATE_LOGIN.setContext(this);
        STATE_UNLOGIN.setContext(this);
    }

    public void setState(UserState state){
        this.currentState = state;
    }

    public UserState getState(){
        return this.currentState;
    }

    public void favorite(){
        this.currentState.favorite();
    }

    public void comment(String comment){
        this.currentState.comment(comment);
    }
}
