package com.imooc.security.core.properties;

public class SocialProperties {
	
	private String filterProcessUrl = "/auth";
	
	private QQProperties qq = new QQProperties();
	
	private WeixinProperties weixin = new WeixinProperties();

	public QQProperties getQq() {
		return qq;
	}

	public void setQq(QQProperties qq) {
		this.qq = qq;
	}

	public WeixinProperties getWeixin() {
		return weixin;
	}

	public void setWeixin(WeixinProperties weixin) {
		this.weixin = weixin;
	}

	public String getFilterProcessUrl() {
		return filterProcessUrl;
	}

	public void setFilterProcessUrl(String filterProcessUrl) {
		this.filterProcessUrl = filterProcessUrl;
	}
}
