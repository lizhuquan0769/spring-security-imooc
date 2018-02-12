package com.imooc.security.core.properties;

import com.imooc.security.core.properties.contsant.AuthenticationResponseTypeEnum;
import com.imooc.security.core.properties.contsant.SecurityConstants;

public class BrowserProperties {
	private String signUpPageUrl = SecurityConstants.DEFAULT_SINGUP_PAGE_URL;
	private String signUpProcessUrl = SecurityConstants.DEFAULT_SINGUP_PROCESS_URL;
	private String unAuthenticationUrl = SecurityConstants.DEFAULT_UNAUTHENTICATION_URL;
	private String loginPageUrl = SecurityConstants.DEFAULT_LOGIN_PAGE_URL;
	private String validateCodeUrlImage = SecurityConstants.DEFAULT_VALIDATE_CODE_URL_IMAGE;
	private String validateCodeUrlSms = SecurityConstants.DEFAULT_VALIDATE_CODE_URL_SMS;
	private String loginProcessUrlForm = SecurityConstants.DEFAULT_LOGIN_PROCESS_URL_FORM;
	private String loginProcessUrlMobile = SecurityConstants.DEFAULT_LOGIN_PROCESS_URL_MOBILE;
	private AuthenticationResponseTypeEnum authentionResponseType = AuthenticationResponseTypeEnum.JSON;
	private int rememberMeSeconds = 604800;
	
	private SessionProperties session = new SessionProperties();

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

	public String getLoginPageUrl() {
		return loginPageUrl;
	}

	public void setLoginPageUrl(String loginPageUrl) {
		this.loginPageUrl = loginPageUrl;
	}

	public String getLoginProcessUrlForm() {
		return loginProcessUrlForm;
	}

	public void setLoginProcessUrlForm(String loginProcessUrlForm) {
		this.loginProcessUrlForm = loginProcessUrlForm;
	}

	public String getLoginProcessUrlMobile() {
		return loginProcessUrlMobile;
	}

	public void setLoginProcessUrlMobile(String loginProcessUrlMobile) {
		this.loginProcessUrlMobile = loginProcessUrlMobile;
	}

	public String getValidateCodeUrlImage() {
		return validateCodeUrlImage;
	}

	public void setValidateCodeUrlImage(String validateCodeUrlImage) {
		this.validateCodeUrlImage = validateCodeUrlImage;
	}

	public String getValidateCodeUrlSms() {
		return validateCodeUrlSms;
	}

	public void setValidateCodeUrlSms(String validateCodeUrlSms) {
		this.validateCodeUrlSms = validateCodeUrlSms;
	}

	public String getSignUpPageUrl() {
		return signUpPageUrl;
	}

	public void setSignUpPageUrl(String signUpUrl) {
		this.signUpPageUrl = signUpUrl;
	}

	public String getSignUpProcessUrl() {
		return signUpProcessUrl;
	}

	public void setSignUpProcessUrl(String signUpProcessUrl) {
		this.signUpProcessUrl = signUpProcessUrl;
	}

	public SessionProperties getSession() {
		return session;
	}

	public void setSession(SessionProperties session) {
		this.session = session;
	}
}
