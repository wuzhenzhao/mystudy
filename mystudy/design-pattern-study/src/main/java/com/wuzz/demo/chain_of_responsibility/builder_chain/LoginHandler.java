package com.wuzz.demo.chain_of_responsibility.builder_chain;

import com.wuzz.demo.chain_of_responsibility.Member;

/**
 * @description:
 * @author: Wuzhenzhao
 * @time 2020/3/23 18:30
 * @since 1.0
 **/
public class LoginHandler extends Handler {
    @Override
    public void doHandler(Member member) {
        System.out.println("登录成功！");
        member.setRoleName("管理员");
        next.doHandler(member);
    }
}
