package com.imooc.security.core.social.qq.connect;

import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

import com.imooc.security.core.social.qq.api.QQ;
import com.imooc.security.core.social.qq.api.QQUserInfo;

public class QQAdapter implements ApiAdapter<QQ>{

	@Override
	public boolean test(QQ api) {
		return true;
	}

	@Override
	public void setConnectionValues(QQ api, ConnectionValues values) {
		QQUserInfo userInfo = api.getUserInfo();
		System.out.println(userInfo);
		
		values.setDisplayName("displayName"); // 昵称
		values.setImageUrl("imageUrl"); // 图像url
		values.setProfileUrl("profileUrl"); //个人主页
		values.setProviderUserId("providerUserId"); // openid
	}

	@Override
	public UserProfile fetchUserProfile(QQ api) {
		return null;
	}

	@Override
	public void updateStatus(QQ api, String message) {
		
	}

}
