package com.imooc.security.browser.authencation;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.imooc.security.core.properties.SecurityProperties;
import com.imooc.security.core.properties.contsant.AuthenticationResponseTypeEnum;

public class ImoocAuthencationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	/**
	 * spring启动的时候会自动注册
	 */
	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private SecurityProperties securityProperties;
	
	/**
	 * Authentication是spring security的核心接口，封装了认证信息
	 */
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		logger.info("imooc登陆成功");
		
		if (AuthenticationResponseTypeEnum.JSON.equals(securityProperties.getBrowser().getAuthentionResponseType())) {
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().write(objectMapper.writeValueAsString(authentication));
		} else if (AuthenticationResponseTypeEnum.DIRECT.equals(securityProperties.getBrowser().getAuthentionResponseType())) {
			// 跳转
			super.onAuthenticationSuccess(request, response, authentication);
		}
	}

}
