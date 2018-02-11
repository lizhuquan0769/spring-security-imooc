package com.imooc.security.core.properties;

public class SocialProperties {
	
	private String filterProcessUrl = "/auth";
	
	private QQProperties qq = new QQProperties();

	public QQProperties getQq() {
		return qq;
	}

	public void setQq(QQProperties qq) {
		this.qq = qq;
	}

	public String getFilterProcessUrl() {
		return filterProcessUrl;
	}

	public void setFilterProcessUrl(String filterProcessUrl) {
		this.filterProcessUrl = filterProcessUrl;
	}
}
