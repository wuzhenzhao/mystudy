/**
 *
 */
package com.wuzz.demo.security.config.validate.code.sms;

/**
 * @author zhailiang
 *
 */
public interface SmsCodeSender {

	void send(String mobile, String code);

}
