package com.wuzz.demo.decorator;

import com.wuzz.demo.adapter.ISigninService;
import com.wuzz.demo.adapter.ResultMsg;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/3/22
 * Time: 11:33
 * Description 描述:对于适配器模式更注重的是兼容，所以采取的方案是对原有固定的业务实现类进行一个拓展继承
 * <p>
 * 而在装饰者模式中注重的是覆盖、扩展。比如Spring中的 IO流的包装，所采用的就是这个模式。
 * 装饰者和被装饰者都要实现同一个接口，主要目的是为了扩展，依旧保留OOP关系（同宗同源）。
 */
public interface ISigninForThirdService extends ISigninService {

    ResultMsg loginForQQ(String openId);

    ResultMsg loginForWechat(String openId);

    ResultMsg loginForTelphone(String telphone, String code);

    ResultMsg loginForRegist(String username, String password);
}
