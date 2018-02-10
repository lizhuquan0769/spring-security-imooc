package com.imooc.security.core.social.qq.connect;

import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;
import org.springframework.social.oauth2.OAuth2Template;

import com.imooc.security.core.social.qq.api.QQ;
import com.imooc.security.core.social.qq.api.QQImpl;

public class QQServiceProvider extends AbstractOAuth2ServiceProvider<QQ> {
	
	private static final String URL_AUTHORIZE = "https://graph.qq.com/oauth2.0/authorize";
			
	private static final String URL_ACCESSTOKEN = "https://graph.qq.com/oauth2.0/token";
	
	private String appId;
	
	public QQServiceProvider(String appId, String appSecret) {
		// authorizeUrl 用户导向url
		// accessTokenUrl 申请令牌地址
		super(new OAuth2Template(appId, appSecret, URL_AUTHORIZE, URL_ACCESSTOKEN));
	}
	
	@Override
	public QQ getApi(String accessToken) {
		return new QQImpl(accessToken, appId);
	}
}
