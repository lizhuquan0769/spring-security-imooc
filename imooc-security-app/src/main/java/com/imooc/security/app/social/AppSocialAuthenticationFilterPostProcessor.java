package com.imooc.security.app.social;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.social.security.SocialAuthenticationFilter;

import com.imooc.security.core.social.SocialAuthenticationFilterPostProcessor;

public class AppSocialAuthenticationFilterPostProcessor implements SocialAuthenticationFilterPostProcessor {

	@Autowired
	private AuthenticationSuccessHandler authenticationSuccessHandler;
	
	@Override
	public void process(SocialAuthenticationFilter socialAuthenticationFilter) {
		socialAuthenticationFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler);
	}

	
}
