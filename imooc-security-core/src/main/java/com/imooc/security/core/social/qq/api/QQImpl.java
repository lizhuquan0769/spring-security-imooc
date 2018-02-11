package com.imooc.security.core.social.qq.api;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * QQ访问用户数据获取API接口
 * 接口文档: http://wiki.connect.qq.com/get_user_info
 * @author lizhuquan
 *
 */
public class QQImpl extends AbstractOAuth2ApiBinding implements QQ {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private static final String URL_GET_OPENID = "https://graph.qq.com/oauth2.0/me?access_token=%s";
	
	private static final String URL_GET_USERINFO = "https://graph.qq.com/user/get_user_info?&oauth_consumer_key=%s&openid=%s";
	
	private String appId;
	
	private String openId;
	
	private ObjectMapper objectMapper = new ObjectMapper();
	
	public QQImpl(String accessToken, String appId) {
		// restTemplate发请求的时候, qq文档要求accessToken放在url参数上
		super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);
		this.appId = appId;
		
		// 根据accessToken换取openid
		String openIdUrl = String.format(URL_GET_OPENID, accessToken);
		String openIdResult = getRestTemplate().getForObject(openIdUrl, String.class);
		logger.info(openIdResult);
		
		this.openId = StringUtils.substringBetween(openIdResult, "\"opendid\":\"", "\"}");
	}
	
	@Override
	public QQUserInfo getUserInfo() {
		String userInfoUrl = String.format(URL_GET_USERINFO, appId, openId);
		String userInfoResult = getRestTemplate().getForObject(userInfoUrl, String.class);
		logger.info(userInfoResult);
		
		QQUserInfo userInfo = null;
		try {
			userInfo = objectMapper.readValue(userInfoResult, QQUserInfo.class);
		} catch (Exception e) {
			throw new RuntimeException("获取qq用户信息失败");
		}
		return userInfo;
	}

}
