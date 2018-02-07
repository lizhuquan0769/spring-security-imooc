package com.imooc.security.browser.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.imooc.security.browser.support.SimpleResponse;
import com.imooc.security.core.properties.SecurityProperties;

@RestController
public class BrowserSecurityController {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	// 重定向到身份验证url前， 会在Session塞一个SavedRequest对象（具体可查看源码ExceptionTranslationFilter.sendStartAuthentication方法）， 
	// 通过RequestCache.getRequest()可获取到
	private RequestCache requestCache = new HttpSessionRequestCache();
	
	// 工具类，方便拼装重定向url
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
	@Autowired
	private SecurityProperties securityProperties;
	
	/**
	 * 当没身份校验时的登陆调试跳转逻辑
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping("${imooc.security.browser.authentication-dispatch-uri}")
	@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
	public SimpleResponse requireAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException {
		SavedRequest savedRequest = requestCache.getRequest(request, response);
		
		if (savedRequest != null) {
			String targetUrl = savedRequest.getRedirectUrl();
			logger.info("引发跳转的请求是：" + targetUrl);
			if (StringUtils.endsWithIgnoreCase(targetUrl, ".html")) {
				// 如果是页面请求, 重定向回去
				redirectStrategy.sendRedirect(request, response, securityProperties.getBrowser().getImageLoginPage());
				return null;
			}
		}
		
		return new SimpleResponse("访问的服务需要身份验证, 请引导用户到登陆页");
	}
}
