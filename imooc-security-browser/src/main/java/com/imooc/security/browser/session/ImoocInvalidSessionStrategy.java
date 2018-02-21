/**
 * 
 */
package com.imooc.security.browser.session;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.security.web.util.UrlUtils;
import org.springframework.util.Assert;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.imooc.security.core.support.SimpleResponse;

/**
 * @author lizhuquan
 *
 */
public class ImoocInvalidSessionStrategy implements InvalidSessionStrategy {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	/**
	 * 跳转的url
	 */
	private String destinationUrl;
	/**
	 * 重定向策略
	 */
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	/**
	 * 跳转前是否创建新的session
	 */
	private boolean createNewSession = true;
	
	private ObjectMapper objectMapper = new ObjectMapper();
	
	public ImoocInvalidSessionStrategy(String invalidSessionUrl) {
		Assert.isTrue(UrlUtils.isValidRedirectUrl(invalidSessionUrl), "url must start with '/' or with 'http(s)'");
		this.destinationUrl = invalidSessionUrl;
	}

	@Override
	public void onInvalidSessionDetected(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		if (createNewSession) {
			request.getSession();
		}

		String sourceUrl = request.getRequestURI();

		if (StringUtils.endsWithIgnoreCase(sourceUrl, ".html")) {
			logger.info("session失效,跳转到" + destinationUrl);
			redirectStrategy.sendRedirect(request, response, destinationUrl);
		}else{
			String message = "session已失效";
			if(isConcurrency()){
				message = message + "，有可能是并发登录导致的，可自定义InvalidSessionStrategy的Bean来覆盖默认逻辑";
			}
			response.setStatus(HttpStatus.UNAUTHORIZED.value());
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().write(objectMapper.writeValueAsString(new SimpleResponse(message)));
		}
	}
	
	/**
	 * session失效是否是并发导致的
	 * @return
	 */
	protected boolean isConcurrency() {
		return false;
	}

	/**
	 * Determines whether a new session should be created before redirecting (to
	 * avoid possible looping issues where the same session ID is sent with the
	 * redirected request). Alternatively, ensure that the configured URL does
	 * not pass through the {@code SessionManagementFilter}.
	 *
	 * @param createNewSession
	 *            defaults to {@code true}.
	 */
	public void setCreateNewSession(boolean createNewSession) {
		this.createNewSession = createNewSession;
	}

}
