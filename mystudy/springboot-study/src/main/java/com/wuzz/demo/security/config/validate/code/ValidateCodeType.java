/**
 *
 */
package com.wuzz.demo.security.config.validate.code;

/**
 * @author zhailiang
 *
 */
public enum ValidateCodeType {

    /**
     * 短信验证码
     */
    SMS {
        @Override
        public String getParamNameOnValidate() {
            return "smsCode";
        }
    },
    /**
     * 图片验证码
     */
    IMAGE {
        @Override
        public String getParamNameOnValidate() {
            return "imageCode";
        }
    };

    /**
     * 校验时从请求中获取的参数的名字
     * @return
     */
    public abstract String getParamNameOnValidate();

}
