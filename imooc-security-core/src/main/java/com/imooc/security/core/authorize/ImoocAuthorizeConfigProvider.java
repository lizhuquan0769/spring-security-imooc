package com.imooc.security.core.authorize;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

import com.imooc.security.core.properties.SecurityProperties;

@Component
public class ImoocAuthorizeConfigProvider implements AuthorizeConfigProvider {

	@Autowired
	private SecurityProperties securityProperties; 
	
	@Override
	public void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
		config.antMatchers(
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
			.permitAll();
	}

}
