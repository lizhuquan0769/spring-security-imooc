package com.imooc.security.core.social.qq.connect;

import org.springframework.social.connect.support.OAuth2ConnectionFactory;

import com.imooc.security.core.social.qq.api.QQ;

/**
 * 1. 产生connection， 然后connection会被封装成SocialAuthenticationToke
 * @author Administrator
 *
 */
public class QQConnectionFactory extends OAuth2ConnectionFactory<QQ>{

	public QQConnectionFactory(String providerId, String appId, String appSecret) {
		super(providerId, new QQServiceProvider(appId, appSecret), new QQAdapter());
	}
}
