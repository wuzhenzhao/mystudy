package com.wuzz.demo.chain_of_responsibility.chain;

import com.wuzz.demo.chain_of_responsibility.Member;

/**
 * @description:
 * @author: Wuzhenzhao
 * @time 2020/3/23 18:26
 * @since 1.0
 **/
public class AuthHandler extends Handler {
    @Override
    public void doHandler(Member member) {
        if (!"管理员".equals(member.getRoleName())) {
            System.out.println("您不是管理员，没有操作权限");
            return;
        }
        System.out.println("允许操作");
    }
}
