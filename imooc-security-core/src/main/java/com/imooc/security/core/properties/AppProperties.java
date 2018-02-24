package com.imooc.security.core.properties;

import com.imooc.security.core.properties.contsant.SecurityConstants;

public class AppProperties {
	
	private String signinProcessUrlOpenId = SecurityConstants.DEFAULT_SIGNIN_PROCESS_URL_OPENID;
	

	public String getSigninProcessUrlOpenId() {
		return signinProcessUrlOpenId;
	}

	public void setSigninProcessUrlOpenId(String signinProcessUrlOpenId) {
		this.signinProcessUrlOpenId = signinProcessUrlOpenId;
	}
}
