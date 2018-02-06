package com.imooc.security.core.validate.core;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

public class ValidateCodeFilter extends OncePerRequestFilter {

	@Autowired
	private AuthenticationFailureHandler authenticationFailureHandler;
	
	private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		if (StringUtils.equals("/authentication/form", request.getRequestURI())
				&& StringUtils.equalsIgnoreCase("post", request.getMethod())) {
			try {
				validate(new ServletWebRequest(request));
			} catch (ValidateCodeException e) {
				
			}
		}
		
		filterChain.doFilter(request, response);
	}

	private void validate(ServletWebRequest request) {
		sessionStrategy.getAttribute(request, ValidateCodeController.SESSION_KEY);
		
		// 验证码不能为空
		
		// 验证码卡不存在
		
		// 验证码已过期
		
		// 验证码不匹配
	}

}
