package com.imooc.security.core.properties;

import com.imooc.security.core.properties.contsant.AuthenticationResponseTypeEnum;
import com.imooc.security.core.properties.contsant.SecurityConstants;

public class BrowserProperties {
	private String signupPageUrl = SecurityConstants.DEFAULT_SINGUP_PAGE_URL;
	private String signupProcessUrl = SecurityConstants.DEFAULT_SINGUP_PROCESS_URL;
	private String unAuthenticationUrl = SecurityConstants.DEFAULT_UNAUTHENTICATION_URL;
	private String signinPageUrl = SecurityConstants.DEFAULT_SIGNIN_PAGE_URL;
	private String validateCodeUrlImage = SecurityConstants.DEFAULT_VALIDATE_CODE_URL_IMAGE;
	private String validateCodeUrlSms = SecurityConstants.DEFAULT_VALIDATE_CODE_URL_SMS;
	private String signinProcessUrlForm = SecurityConstants.DEFAULT_SIGNIN_PROCESS_URL_FORM;
	private String signinProcessUrlMobile = SecurityConstants.DEFAULT_SIGNIN_PROCESS_URL_MOBILE;
	private AuthenticationResponseTypeEnum authentionResponseType = AuthenticationResponseTypeEnum.JSON;
	private int rememberMeSeconds = 604800;
	private String signoutUrl = SecurityConstants.DEFAULT_SIGNOUT_URL;
	private String signoutSuccessUrl = SecurityConstants.DEFAULT_SIGNOUT_SUCCESS_URL;
	private String[] signoutDeleteCookies = SecurityConstants.DEFAULT_SIGNOUT_DELETE_COOKIES;
	
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

	public String getSigninPageUrl() {
		return signinPageUrl;
	}

	public void setSigninPageUrl(String signinPageUrl) {
		this.signinPageUrl = signinPageUrl;
	}

	public String getSigninProcessUrlForm() {
		return signinProcessUrlForm;
	}

	public void setSigninProcessUrlForm(String signinProcessUrlForm) {
		this.signinProcessUrlForm = signinProcessUrlForm;
	}

	public String getSigninProcessUrlMobile() {
		return signinProcessUrlMobile;
	}

	public void setSigninProcessUrlMobile(String signinProcessUrlMobile) {
		this.signinProcessUrlMobile = signinProcessUrlMobile;
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

	public String getSignupPageUrl() {
		return signupPageUrl;
	}

	public void setSignupPageUrl(String signupPageUrl) {
		this.signupPageUrl = signupPageUrl;
	}

	public String getSignupProcessUrl() {
		return signupProcessUrl;
	}

	public void setSignupProcessUrl(String signupProcessUrl) {
		this.signupProcessUrl = signupProcessUrl;
	}

	public SessionProperties getSession() {
		return session;
	}

	public void setSession(SessionProperties session) {
		this.session = session;
	}

	public String getSignoutUrl() {
		return signoutUrl;
	}

	public void setSignoutUrl(String signoutUrl) {
		this.signoutUrl = signoutUrl;
	}

	public String getSignoutSuccessUrl() {
		return signoutSuccessUrl;
	}

	public void setSignoutSuccessUrl(String signoutSuccessUrl) {
		this.signoutSuccessUrl = signoutSuccessUrl;
	}

	public String[] getSignoutDeleteCookies() {
		return signoutDeleteCookies;
	}

	public void setSignoutDeleteCookies(String[] signoutDeleteCookies) {
		this.signoutDeleteCookies = signoutDeleteCookies;
	}
}
