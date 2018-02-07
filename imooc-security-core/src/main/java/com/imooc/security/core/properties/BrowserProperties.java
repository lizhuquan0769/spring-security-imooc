package com.imooc.security.core.properties;

import com.imooc.security.core.properties.contsant.AuthenticationResponseTypeEnum;

public class BrowserProperties {
	
	private String authenticationDispatchUri = "/authentication/dispatch";
	private String loginPage = "/imooc-signin.html";
	private AuthenticationResponseTypeEnum authentionResponseType = AuthenticationResponseTypeEnum.JSON;
	
	
	private int rememberMeSeconds = 3600;

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

	public String getAuthenticationDispatchUri() {
		return authenticationDispatchUri;
	}

	public void setAuthenticationDispatchUri(String authenticationDispatchUri) {
		this.authenticationDispatchUri = authenticationDispatchUri;
	}

	public String getLoginPage() {
		return loginPage;
	}

	public void setLoginPage(String loginPage) {
		this.loginPage = loginPage;
	}
}
