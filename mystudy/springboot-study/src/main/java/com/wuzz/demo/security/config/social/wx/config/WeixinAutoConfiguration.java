/**
 *
 */
package com.wuzz.demo.security.config.social.wx.config;

import com.wuzz.demo.security.config.social.qq.config.SocialAutoConfigurerAdapter;
import com.wuzz.demo.security.config.social.wx.connect.WeixinConnectionFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactory;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/3/13
 * Time: 18:13
 * Description:微信登录配置
 */
@Configuration
public class WeixinAutoConfiguration extends SocialAutoConfigurerAdapter {

    /*
     * (non-Javadoc)
     *
     * @see
     * org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter
     * #createConnectionFactory()
     */
    @Override
    protected ConnectionFactory<?> createConnectionFactory() {
        return new WeixinConnectionFactory("weixin", "",
                "");
    }


}
