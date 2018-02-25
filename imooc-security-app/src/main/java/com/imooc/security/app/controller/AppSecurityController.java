package com.imooc.security.app.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import com.imooc.security.core.social.AppSignupUtils;
import com.imooc.security.core.support.SocialUserInfo;

@RestController
public class AppSecurityController {
	
	@Autowired
	private ProviderSignInUtils providerSignInUtils;
	
	@Autowired
	private AppSignupUtils appSignupUtils;
	
	@GetMapping("/social/signup")
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public SocialUserInfo getSocialUserInfo(HttpServletRequest request){
		SocialUserInfo userInfo = new SocialUserInfo();
		Connection<?> connection = providerSignInUtils.getConnectionFromSession(new ServletWebRequest(request));
		
		if (connection != null)  {
			userInfo.setProviderId(connection.getKey().getProviderId());
			userInfo.setProviderUserId(connection.getKey().getProviderUserId());
			userInfo.setNickname(connection.getDisplayName());
			userInfo.setHeadimg(connection.getImageUrl());
			appSignupUtils.saveConnectionData(new ServletWebRequest(request), connection.createData());
		}
		
		return userInfo;
	}
}
