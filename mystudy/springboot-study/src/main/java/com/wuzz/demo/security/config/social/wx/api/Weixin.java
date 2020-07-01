/**
 *
 */
package com.wuzz.demo.security.config.social.wx.api;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/3/13
 * Time: 18:13
 * Description:微信API调用接口
 */
public interface Weixin {

	/* (non-Javadoc)
	 * @see com.ymt.pz365.framework.security.social.api.SocialUserProfileService#getUserProfile(java.lang.String)
	 */
	WeixinUserInfo getUserInfo(String openId);

}
