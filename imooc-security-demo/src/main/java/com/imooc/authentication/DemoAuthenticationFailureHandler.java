package com.imooc.authentication;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.imooc.security.browser.support.SimpleResponse;
import com.imooc.security.core.properties.ImoocSecurityProperties;
import com.imooc.security.core.properties.contsant.AuthenticationResponseTypeEnum;

/**
 * 登陆失败处理器demo
 * @author Administrator
 *
 */
@Component
public class DemoAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	/**
	 * spring启动的时候会自动注册
	 */
	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private ImoocSecurityProperties securityProperties;
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		
		logger.info("demo登陆失败");
		
		if (AuthenticationResponseTypeEnum.JSON.equals(securityProperties.getBrowser().getAuthentionResponseType())) {
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().write(objectMapper.writeValueAsString(new SimpleResponse(exception.getMessage())));
		} else if (AuthenticationResponseTypeEnum.DIRECT.equals(securityProperties.getBrowser().getAuthentionResponseType())) {
			super.onAuthenticationFailure(request, response, exception);
		}
		
	}

}
