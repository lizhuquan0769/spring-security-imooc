package com.imooc.security.core.social;

import org.springframework.social.security.SocialAuthenticationFilter;
import org.springframework.social.security.SpringSocialConfigurer;

public class ImoocSpringSocialConfigurer extends SpringSocialConfigurer {
	
	private String filterProcessUrl;
	
	public ImoocSpringSocialConfigurer(String filterProcessUrl) {
		this.filterProcessUrl = filterProcessUrl;
	}
	
	/**
	 * T是过滤器的filter
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected <T> T postProcess(T object) {
		SocialAuthenticationFilter filter = (SocialAuthenticationFilter) super.postProcess(object);
		filter.setFilterProcessesUrl(filterProcessUrl);
		return (T) filter;
	}
}
