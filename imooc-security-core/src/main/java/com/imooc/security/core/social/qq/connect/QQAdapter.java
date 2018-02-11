package com.imooc.security.core.social.qq.connect;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

import com.imooc.security.core.social.qq.api.QQ;
import com.imooc.security.core.social.qq.api.QQUserInfo;

/**
 * 1. 给三方用户数据做适配转换
 * @author Administrator
 *
 */
public class QQAdapter implements ApiAdapter<QQ>{
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Override
	public boolean test(QQ api) {
		return true;
	}

	@Override
	public void setConnectionValues(QQ api, ConnectionValues values) {
		QQUserInfo userInfo = api.getUserInfo();
		logger.info("QQImpl调用获取用户信息： " + userInfo.toString());
		
		values.setDisplayName(userInfo.getNickname()); // 昵称
		values.setImageUrl(userInfo.getFigureurl()); // 图像url
		values.setProfileUrl(null); //个人主页
		values.setProviderUserId(userInfo.getOpenId()); // openid
	}

	@Override
	public UserProfile fetchUserProfile(QQ api) {
		return null;
	}

	@Override
	public void updateStatus(QQ api, String message) {
		
	}

}
