package com.imooc.security.core.properties;

import com.imooc.security.core.properties.contsant.AuthenticationResponseTypeEnum;
import com.imooc.security.core.properties.contsant.SecurityConstants;

public class BrowserProperties {
	
	private String unAuthenticationUrl = SecurityConstants.DEFAULT_UNAUTHENTICATION_URL;
	private String loginPage = SecurityConstants.DEFAULT_LOGIN_PAGE_URL;
	private AuthenticationResponseTypeEnum authentionResponseType = AuthenticationResponseTypeEnum.JSON;
	private int rememberMeSeconds = 604800;

	public AuthenticationResponseTypeEnum getAuthentionResponseType() {
		return authentionResponseType;
	}

	public void setAuthentionResponseType(AuthenticationResponseTypeEnum authentionResponseType) {
		this.authentionResponseType = authentionResponseType;
	}

	public int getRememberMeSeconds() {
		return rememberMeSeconds;
	}

	public void setRememberMeSeconds(int rememberMeSeconds) {
		this.rememberMeSeconds = rememberMeSeconds;
	}

	public String getUnAuthenticationUrl() {
		return unAuthenticationUrl;
	}

	public void setUnAuthenticationUrl(String unAuthenticationUrl) {
		this.unAuthenticationUrl = unAuthenticationUrl;
	}

	public String getLoginPage() {
		return loginPage;
	}

	public void setLoginPage(String loginPage) {
		this.loginPage = loginPage;
	}
}
