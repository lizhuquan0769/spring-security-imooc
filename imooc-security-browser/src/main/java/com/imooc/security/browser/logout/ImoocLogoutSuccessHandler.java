package com.imooc.security.browser.logout;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.imooc.security.core.properties.contsant.SecurityConstants;
import com.imooc.security.core.support.SimpleResponse;

public class ImoocLogoutSuccessHandler implements LogoutSuccessHandler {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private ObjectMapper objectMapper = new ObjectMapper();
	
	private String logoutSuccessUrl;
	
	public ImoocLogoutSuccessHandler(String logoutSuccessUrl) {
		this.logoutSuccessUrl = logoutSuccessUrl;
	}
	
	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		
		logger.info("退出成功");
		
		if (!StringUtils.isBlank(logoutSuccessUrl) && !SecurityConstants.DEFAULT_SIGNOUT_SUCCESS_URL.equals(logoutSuccessUrl)) {
			response.sendRedirect(logoutSuccessUrl);
		} else {
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().write(objectMapper.writeValueAsString(new SimpleResponse("退出成功")));
		}
	}

}
