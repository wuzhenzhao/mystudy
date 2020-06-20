/**
 *
 */
package com.wuzz.demo.security.config.validate.code.sms;

import com.wuzz.demo.security.config.validate.code.ValidateCode;
import com.wuzz.demo.security.config.validate.code.impl.AbstractValidateCodeProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * 短信验证码处理器
 *
 * @author zhailiang
 *
 */
@Component("smsValidateCodeProcessor")
public class SmsCodeProcessor extends AbstractValidateCodeProcessor<ValidateCode> {

    /**
     * 短信验证码发送器
     */
    @Autowired
    private SmsCodeSender smsCodeSender;

    @Override
    protected void send(ServletWebRequest request, ValidateCode validateCode) throws Exception {
        String paramName = "mobile";
        String mobile = ServletRequestUtils.getRequiredStringParameter(request.getRequest(), paramName);
        smsCodeSender.send(mobile, validateCode.getCode());
    }

}
