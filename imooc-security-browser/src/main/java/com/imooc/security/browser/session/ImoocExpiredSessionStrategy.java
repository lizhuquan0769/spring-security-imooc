package com.imooc.security.browser.session;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

public class ImoocExpiredSessionStrategy implements SessionInformationExpiredStrategy {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Override
	public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException, ServletException {
		Object principal = event.getSessionInformation().getPrincipal();
		logger.info("用户" + principal + "由于并发登陆被踢下了");
		
		HttpServletResponse response = event.getResponse();
		response.setContentType("text/plain;charset=UTF-8");
		response.getWriter().write("你的帐号在别的地方登陆了, 你已被挤下线");
	}

}
