package com.imooc.security.app;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.builders.InMemoryClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.imooc.security.app.authentication.ImoocAuthenticationFailureHandler;
import com.imooc.security.app.authentication.ImoocAuthenticationSuccessHandler;
import com.imooc.security.app.social.AppSocialAuthenticationFilterPostProcessor;
import com.imooc.security.core.properties.OAuth2ClientProperties;
import com.imooc.security.core.properties.SecurityProperties;
import com.imooc.security.core.social.SocialAuthenticationFilterPostProcessor;

/**
 * 标注为认证授权服务器
 * @author Administrator
 *
 */
@Configuration
@EnableAuthorizationServer
public class ImoocAuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
	
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
	@ConditionalOnMissingBean(value = SocialAuthenticationFilterPostProcessor.class)
	public SocialAuthenticationFilterPostProcessor socialAuthenticationFilterPostProcessor() {
		return new AppSocialAuthenticationFilterPostProcessor();
	}
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private SecurityProperties securityProperties;
	
	@Autowired
	private TokenStore tokenStore;
	
	@Autowired(required = false)
	private JwtAccessTokenConverter jwtAccessTokenConverter;
	
	@Autowired(required = false)
	private TokenEnhancer jwtTokenEnhancer;
	
	/**
	 * 给TokanEndpoint配置
	 */
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints
			.tokenStore(tokenStore)
			.authenticationManager(authenticationManager)
			.userDetailsService(userDetailsService);
		
		if (jwtAccessTokenConverter != null && jwtTokenEnhancer != null) {
			TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
			List<TokenEnhancer> enhancers = new ArrayList<>();
			enhancers.add(jwtTokenEnhancer);
			enhancers.add(jwtAccessTokenConverter);
			enhancerChain.setTokenEnhancers(enhancers);
			
			endpoints
				.tokenEnhancer(enhancerChain)
				.accessTokenConverter(jwtAccessTokenConverter);
		}
	}
	
	/**
	 * 
	 * 决定能给哪些clientId, clientSecret发令牌
	 */
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		InMemoryClientDetailsServiceBuilder builder = clients.inMemory();
		for (OAuth2ClientProperties config : securityProperties.getOauth2().getClients()) {
			builder.withClient(config.getClientId())
				.secret(config.getClientSecret())
				.accessTokenValiditySeconds(config.getAccessTokenValiditySeconds())
				.authorizedGrantTypes("refresh_token", "password")
				.scopes("all", "read", "write");
		}
	}
}
