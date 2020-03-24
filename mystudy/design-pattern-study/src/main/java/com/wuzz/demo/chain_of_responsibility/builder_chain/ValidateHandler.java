package com.wuzz.demo.chain_of_responsibility.builder_chain;

import com.wuzz.demo.chain_of_responsibility.Member;
import org.springframework.util.StringUtils;

/**
 * @description:
 * @author: Wuzhenzhao@hikvision.com.cn
 * @time 2020/3/23 18:06
 * @since 1.0
 **/
public class ValidateHandler extends Handler {

    @Override
    public void doHandler(Member member) {
        if (StringUtils.isEmpty(member.getLoginName()) ||
                StringUtils.isEmpty(member.getLoginPass())) {
            System.out.println("用户名和密码为空");
            return;
        }
        System.out.println("用户名和密码不为空，可以往下执行");
        next.doHandler(member);
    }
}
