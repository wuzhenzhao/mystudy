package com.wuzz.demo.chain_of_responsibility.old;


import com.wuzz.demo.chain_of_responsibility.Member;
import org.springframework.util.StringUtils;

/**
 * @description:
 * @author: Wuzhenzhao
 *  * @time 2020/3/23 18:00
 * @since 1.0
 **/
public class MemberService {

    public void login(String loginName, String loginPass) {
        if (StringUtils.isEmpty(loginName) ||
                StringUtils.isEmpty(loginPass)) {
            System.out.println("用户名和密码为空");
            return;
        }
        System.out.println("用户名和密码不为空，可以往下执行");

        Member member = checkExists(loginName, loginPass);
        if (null == member) {
            System.out.println("用户不存在");
            return;
        }
        System.out.println("登录成功！");

        if (!"管理员".equals(member.getRoleName())) {
            System.out.println("您不是管理员，没有操作权限");
            return;
        }
        System.out.println("允许操作");

    }

    private Member checkExists(String loginName, String loginPass) {
        Member member = new Member(loginName, loginPass);
        member.setRoleName("管理员");
        return member;
    }

    public static void main(String[] args) {
        MemberService service = new MemberService();
        service.login("wuzz", "666");
    }

}
