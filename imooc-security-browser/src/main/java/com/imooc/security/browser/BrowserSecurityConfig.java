package com.imooc.security.browser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.imooc.security.core.properties.SecurityProperties;

@Configuration
@AutoConfigureAfter(value = BrowserAuthenticationHandlerConfig.class)
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private SecurityProperties securityProperties;
	
	@Autowired
	private AuthenticationSuccessHandler authenticationSuccessHandler;
	
	@Autowired
	private AuthenticationFailureHandler authenticationFailureHandler;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//block 1：表单身份验证
		http
			.formLogin() //表单登陆
			.loginPage("/authentication/require") //表单登陆URL
			.loginProcessingUrl("/authentication/form") //处理登陆请求的URL
			.successHandler(authenticationSuccessHandler) // 登陆成功处理器
			.failureHandler(authenticationFailureHandler) // 登陆失败处理器
		.and()
			.authorizeRequests() //对请求授权
			.antMatchers("/authentication/require", 
					securityProperties.getBrowser().getLoginPage()) //对matchers匹配的请求
				.permitAll() //放行
			.anyRequest() //对其它任何请求
				.authenticated() //需要身份认证
		.and()
			.csrf()
				.disable();
	}
}
