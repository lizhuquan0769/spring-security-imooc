package com.imooc.security.core.validate.code;

import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.context.request.ServletWebRequest;

public abstract class AbstractValidateCodeProcessor<C extends ValidateCode,G extends ValidateCodeGenerator> implements ValidateCodeProcessor {
	
	private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();
	
	@Override
	public void process(ServletWebRequest request) throws Exception {
		C validateCode = generate(request);
		save(request, validateCode);
		send(request, validateCode);
	}
	
	private void save(ServletWebRequest request, ValidateCode validateCode) {
		sessionStrategy.setAttribute(request, ValidateCodeHttpUtils.getValidateCodeSessionKey(validateCode), validateCode);
	}

	@SuppressWarnings("unchecked")
	private C generate(ServletWebRequest request) throws Exception {
		return (C) getValidateCodeGenerator().generate(request);
	}

	protected abstract G getValidateCodeGenerator();
	
	protected abstract void send(ServletWebRequest request, C validateCode) throws Exception;
}
