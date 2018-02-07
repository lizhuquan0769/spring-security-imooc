package com.imooc.security.core.validate.code;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import com.imooc.security.core.properties.SecurityProperties;
import com.imooc.security.core.properties.contsant.ValidateCodeTypeEnum;

public class ValidateCodeFilter extends OncePerRequestFilter implements InitializingBean {

	private AuthenticationFailureHandler authenticationFailureHandler;
	
	private Map<String, ValidateCodeTypeEnum> urlMap = new HashMap<>();
	
	private SecurityProperties securityProperties;
	
	private AntPathMatcher antPathMatcher = new AntPathMatcher();
	
	@Override
	public void afterPropertiesSet() throws ServletException {
		super.afterPropertiesSet();
		
		String[] configUrls = StringUtils.splitByWholeSeparator(securityProperties.getCode().getImage().getUrl(), ",");
		urls.add(securityProperties.getBrowser().getAuthenticationImageLoginUri());
		for (String configUrl : configUrls) {
			urls.add(configUrl);
		}
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		boolean action = false;
		for (String url : urls) {
			if (antPathMatcher.match(url, request.getRequestURI())) {
				action = true;
				break;
			}
		}
		
		if (action) {
			try {
				validate(action, new ServletWebRequest(request));
			} catch (ValidateCodeException e) {
				authenticationFailureHandler.onAuthenticationFailure(request, response, e);
				return;
			}
		}
		
		filterChain.doFilter(request, response);
	}

	private void validate(boolean action, ServletWebRequest request) throws ServletRequestBindingException {
//		ValidateCode codeInSession = (ValidateCode) sessionStrategy.getAttribute(request, ValidateCodeController.SESSION_KEY);
//		
//		String codeInRequest = ServletRequestUtils.getStringParameter(request.getRequest(), "imageCode");
//		
//		// 验证码不能为空
//		if (StringUtils.isBlank(codeInRequest)) {
//			throw new ValidateCodeException("验证码的值不能为空");
//		}
//		
//		// 验证码不存在
//		if (codeInRequest == null) {
//			throw new ValidateCodeException("验证码不存在");
//		}
//		
//		// 验证码已过期
//		if (codeInSession.isExpired()) {
//			sessionStrategy.removeAttribute(request, ValidateCodeController.SESSION_KEY);
//			throw new ValidateCodeException("验证码已过期");
//		}
//		
//		// 验证码不匹配
//		if (!StringUtils.equalsIgnoreCase(codeInSession.getCode(), codeInRequest)) {
//			throw new ValidateCodeException("验证码不匹配");
//		}
//		
//		sessionStrategy.removeAttribute(request, ValidateCodeController.SESSION_KEY);
	}

	public void setAuthenticationFailureHandler(AuthenticationFailureHandler authenticationFailureHandler) {
		this.authenticationFailureHandler = authenticationFailureHandler;
	}

	public void setSecurityProperties(SecurityProperties securityProperties) {
		this.securityProperties = securityProperties;
	}
}
