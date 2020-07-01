/**
 *
 */
package com.wuzz.demo.security.config.social.qq.config;

import com.wuzz.demo.security.config.social.qq.connect.QQConnectionFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactory;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/3/13
 * Time: 18:13
 * Description:
 */
@Configuration
public class QQAutoConfig extends SocialAutoConfigurerAdapter {


    /*
     * (non-Javadoc)
     *
     * @see
     * org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter
     * #createConnectionFactory()
     */
    @Override
    protected ConnectionFactory<?> createConnectionFactory() {
        // /auth/qq SocialAuthenticationFilter中定义了拦截路径为  /auth，qq为这里的pid
        String pid = "callback.do";
        String appId = "";
        String appSecret = "";
        return new QQConnectionFactory(pid, appId, appSecret);
    }

}
