package com.imooc.security.browser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

import com.imooc.security.browser.authentication.ImoocAuthenticationSuccessHandler;
import com.imooc.security.browser.authentication.ImoocAuthenticationFailureHandler;
import com.imooc.security.browser.logout.ImoocLogoutSuccessHandler;
import com.imooc.security.browser.session.ImoocExpiredSessionStrategy;
import com.imooc.security.browser.session.ImoocInvalidSessionStrategy;
import com.imooc.security.core.properties.SecurityProperties;

/**
 * 此处配置可被使用者定制替换的bean
 * @author lizhuquan
 *
 */
@Configuration
public class BrowserSearcurityCustomizableBeanConfig {
	
	@Autowired
	private SecurityProperties securityProperties; 
	
	@Bean
	@ConditionalOnMissingBean(value = AuthenticationSuccessHandler.class)
	public AuthenticationSuccessHandler authenticationSuccessHandler() {
		return new ImoocAuthenticationSuccessHandler();
	}
	
	@Bean
	@ConditionalOnMissingBean(value = AuthenticationFailureHandler.class)
	public AuthenticationFailureHandler authenticationFailureHandler() {
		return new ImoocAuthenticationFailureHandler();
	} 
	
	@Bean
	@ConditionalOnMissingBean(value = SessionInformationExpiredStrategy.class)
	public SessionInformationExpiredStrategy sessionInformationExpiredStrategy() {
		return new ImoocExpiredSessionStrategy();
	}
	
	@Bean
	@ConditionalOnMissingBean(value = InvalidSessionStrategy.class)
	public InvalidSessionStrategy invalidSessionStrategy() {
		return new ImoocInvalidSessionStrategy(securityProperties.getBrowser().getSession().getSessionInvalidRedirectUrl());
	}
	
	@Bean
	@ConditionalOnMissingBean(value = LogoutSuccessHandler.class)
	public LogoutSuccessHandler logoutSuccessHandler() {
		return new ImoocLogoutSuccessHandler(securityProperties.getBrowser().getSignoutSuccessUrl());
	}
}
