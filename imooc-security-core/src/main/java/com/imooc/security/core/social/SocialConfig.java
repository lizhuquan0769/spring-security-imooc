package com.imooc.security.core.social;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.social.security.SpringSocialConfigurer;
import org.springframework.web.servlet.View;

import com.imooc.security.core.properties.SecurityProperties;
import com.imooc.security.core.properties.contsant.SecurityConstants;

@Configuration
@EnableSocial
public class SocialConfig extends SocialConfigurerAdapter {
	
	@Autowired
	private SecurityProperties securityProperties;
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired(required = false)
	private ConnectionSignUp connectionSignUp;
	
	/**
	 * ConnectionFactoryLocator: 查找connectionFactory, 可能有多个QQ,微信等, 会根据条件查找具体实现
	 * TextEncryptor: 数据加解密工具
	 */
	@Override
	public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
		JdbcUsersConnectionRepository jdbcUsersConnectionRepository = new JdbcUsersConnectionRepository(dataSource, connectionFactoryLocator, Encryptors.noOpText());
		jdbcUsersConnectionRepository.setTablePrefix(SecurityConstants.DEFAULT_SOCIAL_USER_CONNECTION_PREFIX);
		if (connectionSignUp != null) {
			jdbcUsersConnectionRepository.setConnectionSignUp(connectionSignUp);
		}
		return jdbcUsersConnectionRepository;
	}
	
	@Bean
	public SpringSocialConfigurer imoocSocialSecurityConfig() {
		ImoocSpringSocialConfigurer configurer = new ImoocSpringSocialConfigurer(securityProperties.getSocial().getFilterProcessUrl());
		configurer.signupUrl(securityProperties.getBrowser().getSignupPageUrl());
		return configurer;
	}
	
	@Bean
	public ProviderSignInUtils providerSignInUtils(ConnectionFactoryLocator connectionFactoryLocator) {
		return new ProviderSignInUtils(connectionFactoryLocator, getUsersConnectionRepository(connectionFactoryLocator));
	}
	
	@Bean("connect/status")
	@ConditionalOnMissingBean(name = "connect/status")
	public View connectStatusView() {
		return new ImoocConnecStatusView();
	}
}
