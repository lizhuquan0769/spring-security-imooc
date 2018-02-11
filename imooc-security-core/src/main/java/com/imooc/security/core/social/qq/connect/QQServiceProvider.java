package com.imooc.security.core.social.qq.connect;

import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;

import com.imooc.security.core.social.qq.api.QQ;
import com.imooc.security.core.social.qq.api.QQImpl;

/**
 * 1. 使用OAuth2Template执行流程
 * 2. 使用QQImple获取三方用户数据
 * @author Administrator
 *
 */
public class QQServiceProvider extends AbstractOAuth2ServiceProvider<QQ> {
	
	private static final String URL_AUTHORIZE = "https://graph.qq.com/oauth2.0/authorize";
			
	private static final String URL_ACCESSTOKEN = "https://graph.qq.com/oauth2.0/token";
	
	private String appId;
	
	
	public QQServiceProvider(String appId, String appSecret) {
		// authorizeUrl 用户导向url
		// accessTokenUrl 申请令牌地址
		super(new QQOAuth2Template(appId, appSecret, URL_AUTHORIZE, URL_ACCESSTOKEN));
		this.appId = appId;
	}
	
	@Override
	public QQ getApi(String accessToken) {
		return new QQImpl(accessToken, appId);
	}
}
