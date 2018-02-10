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
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import com.imooc.security.core.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import com.imooc.security.core.properties.SecurityProperties;
import com.imooc.security.core.validate.code.ValidateCodeSecurityConfig;

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
	private ValidateCodeSecurityConfig validateCodeSecurityConfig;
	
	@Autowired
	private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;
	
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
		
		http
			// 验证码相关配置
			.apply(validateCodeSecurityConfig)
		.and()
			// 验证码登陆相关配置
			.apply(smsCodeAuthenticationSecurityConfig)
		.and()
			// 表单登陆相关配置
			.formLogin()
				.loginPage(securityProperties.getBrowser().getUnAuthenticationUrl()) //表单登陆URL
				.loginProcessingUrl(securityProperties.getBrowser().getLoginProcessUrlForm()) //处理登陆请求的URL
				.successHandler(authenticationSuccessHandler) // 登陆成功处理器
				.failureHandler(authenticationFailureHandler) // 登陆失败处理器
		.and()
			// 记住我
			.rememberMe()
			.tokenRepository(persistentTokenRepository())
			.tokenValiditySeconds(securityProperties.getBrowser().getRememberMeSeconds())
			.userDetailsService(userDetailsService)
		.and()
			// url访问授权配置
			.authorizeRequests() //对请求授权
			.antMatchers(
					securityProperties.getBrowser().getUnAuthenticationUrl(), 
					securityProperties.getBrowser().getLoginPageUrl(),
					securityProperties.getBrowser().getLoginProcessUrlMobile(), 
					securityProperties.getBrowser().getValidateCodeUrlImage(),
					securityProperties.getBrowser().getValidateCodeUrlSms()
					) //对matchers匹配的请求
				.permitAll() //放行
			.anyRequest() //对其它任何请求
				.authenticated() //需要身份认证
		.and()
			// csrf配置
			.csrf()
				.disable();
		
	}
}
