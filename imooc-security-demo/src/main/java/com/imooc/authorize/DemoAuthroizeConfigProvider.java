package com.imooc.authorize;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

import com.imooc.security.core.authorize.AuthorizeConfigProvider;

@Component
public class DemoAuthroizeConfigProvider implements AuthorizeConfigProvider {

	@Override
	public void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
		config.antMatchers("/users").hasRole("ADMIN");
	}

}
