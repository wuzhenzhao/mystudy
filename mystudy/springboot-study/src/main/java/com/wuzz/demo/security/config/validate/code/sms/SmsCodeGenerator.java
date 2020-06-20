/**
 *
 */
package com.wuzz.demo.security.config.validate.code.sms;

import com.wuzz.demo.security.config.validate.code.ValidateCode;
import com.wuzz.demo.security.config.validate.code.ValidateCodeGenerator;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author zhailiang
 *
 */
@Component("smsValidateCodeGenerator")
public class SmsCodeGenerator implements ValidateCodeGenerator {

    /*
     * (non-Javadoc)
     *
     * @see
     * com.imooc.security.core.validate.code.ValidateCodeGenerator#generate(org.
     * springframework.web.context.request.ServletWebRequest)
     */
    @Override
    public ValidateCode generate(ServletWebRequest request) {
        String code = RandomStringUtils.randomNumeric(6);
        return new ValidateCode(code, 60);
    }


}
