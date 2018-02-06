package com.imooc.security.core.properties;

import com.imooc.security.core.properties.contsant.LoginTypeEnum;

public class BrowserProperties {
	
	private String loginPage = "/imooc-signin.html";
	
	private LoginTypeEnum loginType = LoginTypeEnum.JSON;
	
	private int rememberMeSeconds = 3600;

	public String getLoginPage() {
		return loginPage;
	}

	public void setLoginPage(String loginPage) {
		this.loginPage = loginPage;
	}

	public LoginTypeEnum getLoginType() {
		return loginType;
	}

	public void setLoginType(LoginTypeEnum loginType) {
		this.loginType = loginType;
	}

	public int getRememberMeSeconds() {
		return rememberMeSeconds;
	}

	public void setRememberMeSeconds(int rememberMeSeconds) {
		this.rememberMeSeconds = rememberMeSeconds;
	}
}
