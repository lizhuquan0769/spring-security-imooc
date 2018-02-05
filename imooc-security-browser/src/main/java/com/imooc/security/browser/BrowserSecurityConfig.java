package com.imooc.security.browser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.imooc.security.core.properties.SecurityProperties;

@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private SecurityProperties securityProperties;
	
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
		.and()
			.authorizeRequests() //对请求授权
			.antMatchers(securityProperties.getBrowser().getLoginPage()) //对matchers匹配的请求
				.permitAll() //放行
			.anyRequest() //对其它任何请求
				.authenticated() //需要身份认证
		.and()
			.csrf()
				.disable();
	}
}
