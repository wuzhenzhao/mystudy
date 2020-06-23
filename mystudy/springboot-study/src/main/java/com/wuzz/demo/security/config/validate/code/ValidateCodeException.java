/**
 *
 */
package com.wuzz.demo.security.config.validate.code;

import org.springframework.security.core.AuthenticationException;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/3/13
 * Time: 18:13
 * Description:
 */
public class ValidateCodeException extends AuthenticationException {

	/**
	 *
	 */
	private static final long serialVersionUID = -7285211528095468156L;

	public ValidateCodeException(String msg) {
		super(msg);
	}

}
