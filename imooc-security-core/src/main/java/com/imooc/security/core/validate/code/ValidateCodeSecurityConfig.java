package com.imooc.security.core.validate.code;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import com.imooc.security.core.properties.SecurityProperties;

@Component
public class ValidateCodeSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
	
	@Autowired
	private SecurityProperties securityProperties;
	
	@Autowired
	private AuthenticationFailureHandler authenticationFailureHandler;
	
	@Autowired
	private ValidateCodeRepository validateCodeRepository;
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		
		ValidateCodeFilter validateCodeFilter = new ValidateCodeFilter();
		validateCodeFilter.setValidateCodeRepository(validateCodeRepository);
		validateCodeFilter.setAuthenticationFailureHandler(authenticationFailureHandler);
		validateCodeFilter.setSecurityProperties(securityProperties);
		validateCodeFilter.afterPropertiesSet();
		
		http
			.addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class);
	}
	
}
