package com.imooc.security.core.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

public interface ValidateCodeProcessor {
	
	/**
	 * 处理
	 * @param request
	 * @throws Exception
	 */
	void process(ServletWebRequest request) throws Exception;
}
