package com.wuzz.demo.chain_of_responsibility.chain;

import com.wuzz.demo.chain_of_responsibility.Member;

/**
 * @description:
 * @author: Wuzhenzhao
 * @time 2020/3/23 18:31
 * @since 1.0
 **/
public class MemberService {

    public void login(String loginName,String loginPass){
        Handler validateHandler = new ValidateHandler();
        Handler loginHandler = new LoginHandler();
        Handler authHandler = new AuthHandler();

        validateHandler.next(loginHandler);
        loginHandler.next(authHandler);

        validateHandler.doHandler(new Member(loginName,loginPass));

    }

    public static void main(String[] args) {
        MemberService memberService = new MemberService();
        memberService.login("wuzz","666");
    }
}
