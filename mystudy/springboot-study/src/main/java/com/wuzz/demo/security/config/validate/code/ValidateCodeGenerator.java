/**
 *
 */
package com.wuzz.demo.security.config.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/3/13
 * Time: 18:13
 * Description:
 */
public interface ValidateCodeGenerator {
    /**
     * 功能描述: <br>
     * 生成验证码
     * @Param: [request]
     * @Return: com.wuzz.demo.security.config.validate.code.ValidateCode
     * @Author: wuzhenzhao
     * @Date: 2020/6/23 16:01
     */
    ValidateCode generate(ServletWebRequest request);

}
