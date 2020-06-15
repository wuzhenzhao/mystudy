package com.wuzz.demo.web;

import com.wuzz.demo.model.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * @description: 类功能描述
 * @author: wuzhenzhao
 * @time 2020/6/12 19:39
 * @since 1.0
 **/
@RestController
public class LoginController {

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(User user) {
        //添加用户认证信息
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(
                user.getUsername(),
                user.getPassword()
        );
        try {
            //进行验证，这里可以捕获异常，然后返回对应信息
            subject.login(usernamePasswordToken);
//            subject.checkRole("admin");
//            subject.checkPermissions("query", "add");
        } catch (AuthenticationException e) {
            e.printStackTrace();
            return "账号或密码错误！";
        } catch (AuthorizationException e) {
            e.printStackTrace();
            return "没有权限";
        }
        String id = (String) subject.getSession().getId();
        return id;
    }

    //注解验角色和权限
//    @RequiresRoles("admin")
    @RequiresPermissions("add")
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return "index!";
    }


//    @RequestMapping(value = "/logout", method = RequestMethod.GET)
//    public String logout() {
//        Subject subject = SecurityUtils.getSubject();
//        subject.logout();
//        return "redirect:/login";
//    }

    @RequestMapping(value = "/nologin", method = RequestMethod.GET)
    public String nologin() {

        return "nologin!";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String tttt() {

        String id = (String) SecurityUtils.getSubject().getSession().getId();
        return id;
    }

    @RequestMapping("/wuzz/logout")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "login out.....";
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test() {

        return "test!";
    }
}
