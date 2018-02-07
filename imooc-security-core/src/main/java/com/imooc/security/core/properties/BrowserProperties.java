package com.imooc.security.core.properties;

import com.imooc.security.core.properties.contsant.AuthenticationResponseTypeEnum;

public class BrowserProperties {
	
	private String authenticationDispatchUri = "/authentication/dispatch";
	private String authenticationProcessUri = "/authentication/form";
	private AuthenticationResponseTypeEnum authentionResponseType = AuthenticationResponseTypeEnum.JSON;
	private String imageCodeUri = "/code/image";
	private String imageLoginPage = "/imooc-signin-image.html";
	private String smsCodeUri = "/code/sms";
	private String smsLoginPage = "/imooc-singin-sms.html";
	
	
	private int rememberMeSeconds = 3600;

	public String getImageLoginPage() {
		return imageLoginPage;
	}

	public void setImageLoginPage(String imageLoginPage) {
		this.imageLoginPage = imageLoginPage;
	}

	public String getSmsLoginPage() {
		return smsLoginPage;
	}

	public void setSmsLoginPage(String smsLoginPage) {
		this.smsLoginPage = smsLoginPage;
	}

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

	public String getImageCodeUri() {
		return imageCodeUri;
	}

	public void setImageCodeUri(String imageCodeUri) {
		this.imageCodeUri = imageCodeUri;
	}

	public String getSmsCodeUri() {
		return smsCodeUri;
	}

	public void setSmsCodeUri(String smsCodeUri) {
		this.smsCodeUri = smsCodeUri;
	}

	public String getAuthenticationDispatchUri() {
		return authenticationDispatchUri;
	}

	public void setAuthenticationDispatchUri(String authenticationDispatchUri) {
		this.authenticationDispatchUri = authenticationDispatchUri;
	}

	public String getAuthenticationProcessUri() {
		return authenticationProcessUri;
	}

	public void setAuthenticationProcessUri(String authenticationProcessUri) {
		this.authenticationProcessUri = authenticationProcessUri;
	}
}
