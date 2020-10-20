package com.wuzz.demo.chain_of_responsibility.builder_chain;

import com.wuzz.demo.chain_of_responsibility.Member;


/**
 * @description:建造者+责任链
 * @author: Wuzhenzhao
 * @time 2020/3/23 19:09
 * @since 1.0
 **/
public class MemberService {

    public void login(String loginName, String loginPass) {

        Handler.Builder builder = new Handler.Builder();

        builder.addHandler(new ValidateHandler())
                .addHandler(new LoginHandler())
                .addHandler(new AuthHandler());

        builder.build().doHandler(new Member(loginName, loginPass));

    }

    public static void main(String[] args) {
        MemberService memberService = new MemberService();
        memberService.login("wuzz", "666");
    }
}
