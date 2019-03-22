package com.wuzz.demo.delegate;

import java.util.HashMap;
import java.util.Map;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/3/22
 * Time: 10:41
 * Description 描述:被委派者
 */
public class Leader implements ITarget{

    //作为委派者，他必须知道底下的人谁比较擅长干什么
    // 就好比DispatcherServlet 里面哪个handle保存了 controller跟URL的对应关系一样
    private Map<String,ITarget> targets = new HashMap<String,ITarget>();

    public Leader() {
        // 员工A适合干加密工作
        targets.put("加密",new TargetA());
        // 员工B 适合干登陆开发
        targets.put("登录",new TargetB());
    }

    //项目经理自己不干活
    public void doing(String command){
        targets.get(command).doing(command);
    }
}
