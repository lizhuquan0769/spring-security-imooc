package com.imooc.security.core.properties;

import com.imooc.security.core.properties.contsant.SecurityConstants;

public class SessionProperties {
	
	private String sessionInvalidRedirectUrl = SecurityConstants.DEFAULT_INVALID_SESSION_REDIRECT_URL;
	
	private int maxinumSession = SecurityConstants.DEFAULT_MAX_SESSIOIN_PRE_USER;
	
	private boolean maxSessionsPreventsLogin = false;

	public String getSessionInvalidRedirectUrl() {
		return sessionInvalidRedirectUrl;
	}

	public void setSessionInvalidRedirectUrl(String sessionInvalidRedirectUrl) {
		this.sessionInvalidRedirectUrl = sessionInvalidRedirectUrl;
	}

	public int getMaxinumSession() {
		return maxinumSession;
	}

	public void setMaxinumSession(int maxinumSession) {
		this.maxinumSession = maxinumSession;
	}

	public boolean isMaxSessionsPreventsLogin() {
		return maxSessionsPreventsLogin;
	}

	public void setMaxSessionsPreventsLogin(boolean maxSessionsPreventsLogin) {
		this.maxSessionsPreventsLogin = maxSessionsPreventsLogin;
	}
}
