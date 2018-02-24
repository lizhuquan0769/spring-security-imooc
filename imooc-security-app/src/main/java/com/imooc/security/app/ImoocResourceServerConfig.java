package com.imooc.security.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.social.security.SpringSocialConfigurer;

import com.imooc.security.core.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import com.imooc.security.core.properties.SecurityProperties;
import com.imooc.security.core.validate.code.RedisValidateCodeRepository;
import com.imooc.security.core.validate.code.ValidateCodeRepository;
import com.imooc.security.core.validate.code.ValidateCodeSecurityConfig;

import redis.clients.jedis.JedisPoolConfig;

/**
 * 标注为资源服务器
 * @author Administrator
 *
 */
@Configuration
@EnableResourceServer
public class ImoocResourceServerConfig extends ResourceServerConfigurerAdapter {
	
	@Autowired
	private SecurityProperties securityProperties;
	
	@Autowired
	private ValidateCodeSecurityConfig validateCodeSecurityConfig;
	
	@Autowired
	private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;
	
	@Autowired
	private SpringSocialConfigurer imoocSocialSecurityConfig;
	
	@Autowired
	private AuthenticationSuccessHandler authenticationSuccessHandler;
	
	@Autowired
	private AuthenticationFailureHandler authenticationFailureHandler;
	
	@Bean
	@ConditionalOnMissingBean(value = ValidateCodeRepository.class)
	public ValidateCodeRepository validateCodeRepository() {
		return new RedisValidateCodeRepository();
	}
	
    @Bean
    @ConditionalOnMissingBean(value = RedisTemplate.class)
    public RedisTemplate<?, ?> getRedisTemplate(){
		JedisConnectionFactory factory = new JedisConnectionFactory();  
	    factory.setPoolConfig(new JedisPoolConfig()); 
	    factory.afterPropertiesSet();
	    
	    RedisTemplate<?,?> template = new RedisTemplate<>();
	    template.setConnectionFactory(factory);
	    template.afterPropertiesSet();
	    
	    return template;  
    }  
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		
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
//		.logout()
//			// 退出登陆的动作
//			.logoutUrl(securityProperties.getBrowser().getSignoutUrl())
//			.logoutSuccessUrl(securityProperties.getBrowser().getSignoutSuccessUrl())
//			// 退出成功后的重定向地址，logoutSuccessHandler与logoutSuccessUrl互斥， 若配置该项，logoutSuccessUrl配置将失效, 不过handler可以通过该配置逻辑控制
//			.logoutSuccessHandler(logoutSuccessHandler)
//			.deleteCookies(securityProperties.getBrowser().getSignoutDeleteCookies())
//			.and()
		// 记住我
//		.rememberMe()
//			.tokenRepository(persistentTokenRepository())
//			.tokenValiditySeconds(securityProperties.getBrowser().getRememberMeSeconds())
//			.userDetailsService(userDetailsService)
//			.and()
		// session配置
//		.sessionManagement()
//			// session失效时的重定向地址
////			.invalidSessionUrl(securityProperties.getBrowser().getSession().getSessionInvalidRedirectUrl())
//			// session失效的处理器， 此项设置了的话，invalidSessionUrl将失效
//			.invalidSessionStrategy(invalidSessionStrategy)
//			// 同一用户最多产生的session数
//			.maximumSessions(securityProperties.getBrowser().getSession().getMaxinumSession())
//			// 如果超出最大session限制, 则阻止登陆
//			.maxSessionsPreventsLogin(securityProperties.getBrowser().getSession().isMaxSessionsPreventsLogin())
//			// session被踢出时的处理器
//			.expiredSessionStrategy(sessionInformationExpiredStrategy)
//			.and()
//			.and()
		// url访问授权配置
		// 对请求授权
		.authorizeRequests()
			// ant匹配的请求
			.antMatchers(
					securityProperties.getBrowser().getSignupPageUrl(),
					securityProperties.getBrowser().getSignupProcessUrl(),
					securityProperties.getBrowser().getUnAuthenticationUrl(),
					securityProperties.getBrowser().getSigninPageUrl(),
					securityProperties.getBrowser().getSigninProcessUrlMobile(), 
					securityProperties.getBrowser().getValidateCodeUrlImage(),
					securityProperties.getBrowser().getValidateCodeUrlSms(),
					securityProperties.getBrowser().getSession().getSessionInvalidRedirectUrl(),
					securityProperties.getBrowser().getSignoutSuccessUrl()
					)
				//放行
				.permitAll() 
			// 而对其它任何请求
			.anyRequest()
				// 需要身份认证
				.authenticated()
			.and()
		// csrf配置
		.csrf()
			.disable();
	}
}
