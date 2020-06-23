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

	ValidateCode generate(ServletWebRequest request);

}
