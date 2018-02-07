package com.imooc.security.browser;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import com.imooc.security.core.properties.SecurityProperties;
import com.imooc.security.core.validate.code.ValidateCodeFilter;

@Configuration
@AutoConfigureAfter(value = BrowserAuthenticationHandlerConfig.class)
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private SecurityProperties securityProperties;
	
	@Autowired
	private AuthenticationSuccessHandler authenticationSuccessHandler;
	
	@Autowired
	private AuthenticationFailureHandler authenticationFailureHandler;
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private ValidateCodeFilter validateCodeFilter;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public PersistentTokenRepository persistentTokenRepository() {
		JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
		tokenRepository.setDataSource(dataSource);
//		tokenRepository.setCreateTableOnStartup(true);
		return tokenRepository;
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		//block 1：表单身份验证
		http
			.addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)
			.formLogin() //表单登陆
				.loginPage(securityProperties.getBrowser().getAuthenticationDispatchUri()) //表单登陆URL
				.loginProcessingUrl(securityProperties.getBrowser().getAuthenticationImageLoginUri()) //处理登陆请求的URL
				.successHandler(authenticationSuccessHandler) // 登陆成功处理器
				.failureHandler(authenticationFailureHandler) // 登陆失败处理器
		.and()
			// 记住我
			.rememberMe()
			.tokenRepository(persistentTokenRepository())
			.tokenValiditySeconds(securityProperties.getBrowser().getRememberMeSeconds())
			.userDetailsService(userDetailsService)
		.and()
			.authorizeRequests() //对请求授权
			.antMatchers(
					securityProperties.getBrowser().getAuthenticationDispatchUri(), 
					securityProperties.getBrowser().getAuthenticationImageLoginUri(), 
					securityProperties.getBrowser().getAuthenticationSmsLoginUri(), 
					securityProperties.getBrowser().getImageLoginPage(), 
					securityProperties.getBrowser().getImageCodeUri(),
					securityProperties.getBrowser().getSmsLoginPage(), 
					securityProperties.getBrowser().getSmsCodeUri()
					) //对matchers匹配的请求
				.permitAll() //放行
			.anyRequest() //对其它任何请求
				.authenticated() //需要身份认证
		.and()
			.csrf()
				.disable();
	}
}
