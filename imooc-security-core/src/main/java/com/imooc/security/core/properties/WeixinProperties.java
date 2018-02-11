package com.imooc.security.core.properties;

import org.springframework.boot.autoconfigure.social.SocialProperties;

public class WeixinProperties extends SocialProperties {
	
	private String providerId = "weixin";

	public String getProviderId() {
		return providerId;
	}

	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}
}
