package com.imooc.authorize;

import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

import com.imooc.security.core.authorize.AuthorizeConfigProvider;

@Component
@Order(Integer.MAX_VALUE)
public class DemoAuthroizeConfigProvider implements AuthorizeConfigProvider {

	@Override
	public void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
		// anyRequest()需要最后调用，不然后面的配置会失效
		config.anyRequest().access("@rbacService.hasPermission(request, authentication)");
	}

}
