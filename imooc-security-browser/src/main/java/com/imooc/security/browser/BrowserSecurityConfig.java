package com.imooc.security.browser;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.social.security.SpringSocialConfigurer;

import com.imooc.security.core.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import com.imooc.security.core.authorize.AuthorizeConfigManager;
import com.imooc.security.core.properties.SecurityProperties;
import com.imooc.security.core.validate.code.ValidateCodeSecurityConfig;

@Configuration
@AutoConfigureAfter(value = BrowserSearcurityCustomizableBeanConfig.class)
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
	private SessionInformationExpiredStrategy sessionInformationExpiredStrategy;
	
	@Autowired
	private InvalidSessionStrategy invalidSessionStrategy;
	
	@Autowired
	private LogoutSuccessHandler logoutSuccessHandler;
	
	@Autowired
	private ValidateCodeSecurityConfig validateCodeSecurityConfig;
	
	@Autowired
	private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;
	
	@Autowired
	private SpringSocialConfigurer imoocSocialSecurityConfig;
	
	@Autowired
	private AuthorizeConfigManager authorizeConfigManager;
	
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
		// 验证码校验相关配置
		.apply(validateCodeSecurityConfig)
			.and()
		// 验证码登陆相关配置
		.apply(smsCodeAuthenticationSecurityConfig)
			.and()
		// social登陆相关配置
		.apply(imoocSocialSecurityConfig)
			.and()
		// 表单登陆相关配置
		.formLogin()
			.loginPage(securityProperties.getBrowser().getUnAuthenticationUrl()) //表单登陆URL
			.loginProcessingUrl(securityProperties.getBrowser().getSigninProcessUrlForm()) //处理登陆请求的URL
			.successHandler(authenticationSuccessHandler) // 登陆成功处理器
			.failureHandler(authenticationFailureHandler) // 登陆失败处理器
			.and()
		// 退出登陆相关配置
		.logout()
			// 退出登陆的动作
			.logoutUrl(securityProperties.getBrowser().getSignoutUrl())
			.logoutSuccessUrl(securityProperties.getBrowser().getSignoutSuccessUrl())
			// 退出成功后的重定向地址，logoutSuccessHandler与logoutSuccessUrl互斥， 若配置该项，logoutSuccessUrl配置将失效, 不过handler可以通过该配置逻辑控制
			.logoutSuccessHandler(logoutSuccessHandler)
			.deleteCookies(securityProperties.getBrowser().getSignoutDeleteCookies())
			.and()
		// 记住我
		.rememberMe()
			.tokenRepository(persistentTokenRepository())
			.tokenValiditySeconds(securityProperties.getBrowser().getRememberMeSeconds())
			.userDetailsService(userDetailsService)
			.and()
		// session配置
		.sessionManagement()
			// session失效时的重定向地址
//			.invalidSessionUrl(securityProperties.getBrowser().getSession().getSessionInvalidRedirectUrl())
			// session失效的处理器， 此项设置了的话，invalidSessionUrl将失效
			.invalidSessionStrategy(invalidSessionStrategy)
			// 同一用户最多产生的session数
			.maximumSessions(securityProperties.getBrowser().getSession().getMaxinumSession())
			// 如果超出最大session限制, 则阻止登陆
			.maxSessionsPreventsLogin(securityProperties.getBrowser().getSession().isMaxSessionsPreventsLogin())
			// session被踢出时的处理器
			.expiredSessionStrategy(sessionInformationExpiredStrategy)
			.and()
			.and()
		// csrf配置
		.csrf()
			.disable();
		
		authorizeConfigManager.config(http.authorizeRequests());
	}
}
