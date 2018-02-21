package com.imooc.security.app;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.imooc.security.app.authentication.ImoocAuthenticationFailureHandler;
import com.imooc.security.app.authentication.ImoocAuthenticationSuccessHandler;

/**
 * 标注为认证授权服务器
 * @author Administrator
 *
 */
@Configuration
@EnableAuthorizationServer
public class ImoocAuthorizationServerConfig {
	
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
}
