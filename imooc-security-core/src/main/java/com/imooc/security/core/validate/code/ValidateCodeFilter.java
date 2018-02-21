package com.imooc.security.core.validate.code;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import com.imooc.security.core.properties.SecurityProperties;
import com.imooc.security.core.properties.contsant.ValidateCodeTypeEnum;

public class ValidateCodeFilter extends OncePerRequestFilter implements InitializingBean {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private AuthenticationFailureHandler authenticationFailureHandler;
	
	private SecurityProperties securityProperties;
	
	private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();
	
	private Map<String, ValidateCodeTypeEnum> urlMap = new LinkedHashMap<>();
	
	private AntPathMatcher antPathMatcher = new AntPathMatcher();
	
	@Override
	public void afterPropertiesSet() throws ServletException {
		super.afterPropertiesSet();
		
		urlMap.put(securityProperties.getBrowser().getSigninProcessUrlForm(), ValidateCodeTypeEnum.IMAGE);
		urlMap.put(securityProperties.getBrowser().getSigninProcessUrlMobile(), ValidateCodeTypeEnum.SMS);
		
		addUrlToMap(securityProperties.getCode().getImage().getUrl(), ValidateCodeTypeEnum.IMAGE);
		addUrlToMap(securityProperties.getCode().getSms().getUrl(), ValidateCodeTypeEnum.SMS);
	}
	
	private void addUrlToMap(String validateCodeUrl, ValidateCodeTypeEnum validateCodeType) {
		String[] validateCodeUrls = StringUtils.splitByWholeSeparator(validateCodeUrl, ",");
		for (String url : validateCodeUrls) {
			urlMap.put(url, validateCodeType);
		}
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		logger.info("ValidateCodeFilter: " + request.getRequestURL());
		
		ValidateCodeTypeEnum validateCodeType = null;
		for (Entry<String, ValidateCodeTypeEnum> entry : urlMap.entrySet()) {
			if (antPathMatcher.match(entry.getKey(), request.getRequestURI())) {
				validateCodeType = entry.getValue();
				break;
			}
		}
		
		if (validateCodeType != null) {
			try {
				validate(new ServletWebRequest(request), validateCodeType);
			} catch (ValidateCodeException e) {
				authenticationFailureHandler.onAuthenticationFailure(request, response, e);
				return;
			}
		}
		
		filterChain.doFilter(request, response);
	}

	private void validate(ServletWebRequest request, ValidateCodeTypeEnum validateCodeType) throws ServletRequestBindingException {
		
		String sessionKey = ValidateCodeHttpUtils.getValidateCodeSessionKey(validateCodeType);
		
		Object objectInSession = sessionStrategy.getAttribute(request, sessionKey);
		if (objectInSession == null) {
			throw new ValidateCodeException(String.format("需要%s验证码", validateCodeType));
		}
		
		ValidateCode codeInSession = (ValidateCode) objectInSession;
		
		String codeInRequest = ValidateCodeHttpUtils.getValidateCodeInRequest(request, validateCodeType);
		
		// 验证码不能为空
		if (StringUtils.isBlank(codeInRequest)) {
			throw new ValidateCodeException("请填写验证码");
		}
		
		// 验证码不存在
		if (codeInRequest == null) {
			throw new ValidateCodeException("请先获取验证码");
		}
		
		// 验证码已过期
		if (codeInSession.isExpired()) {
			sessionStrategy.removeAttribute(request, sessionKey);
			throw new ValidateCodeException("验证码已过期");
		}
		
		// 验证码不匹配
		if (!StringUtils.equalsIgnoreCase(codeInSession.getCode(), codeInRequest)) {
			throw new ValidateCodeException("验证码不匹配");
		}
		
		sessionStrategy.removeAttribute(request, sessionKey);
	}

	public AuthenticationFailureHandler getAuthenticationFailureHandler() {
		return authenticationFailureHandler;
	}

	public void setAuthenticationFailureHandler(AuthenticationFailureHandler authenticationFailureHandler) {
		this.authenticationFailureHandler = authenticationFailureHandler;
	}

	public SecurityProperties getSecurityProperties() {
		return securityProperties;
	}

	public void setSecurityProperties(SecurityProperties securityProperties) {
		this.securityProperties = securityProperties;
	}
}
