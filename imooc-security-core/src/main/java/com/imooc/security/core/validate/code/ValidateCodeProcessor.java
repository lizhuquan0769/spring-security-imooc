package com.imooc.security.core.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

public interface ValidateCodeProcessor {
	
	String SESSION_KEY_PREFIX = "SESSION_KEY_FOR_CODE_";
	
	/**
	 * 处理
	 * @param request
	 * @throws Exception
	 */
	void process(ServletWebRequest request) throws Exception;
}
