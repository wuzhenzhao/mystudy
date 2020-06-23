/**
 *
 */
package com.wuzz.demo.security.config.validate.code.sms;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/3/13
 * Time: 18:13
 * Description:
 */
public interface SmsCodeSender {

	void send(String mobile, String code);

}
