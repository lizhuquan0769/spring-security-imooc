package com.imooc.security.core.properties;

import com.imooc.security.core.properties.contsant.LoginTypeEnum;

public class BrowserProperties {
	
	private String loginPage = "/imooc-signin.html";
	
	private LoginTypeEnum loginType = LoginTypeEnum.JSON;

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
}
