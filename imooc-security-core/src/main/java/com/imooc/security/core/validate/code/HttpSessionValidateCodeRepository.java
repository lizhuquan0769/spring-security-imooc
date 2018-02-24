package com.imooc.security.core.validate.code;

import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.context.request.ServletWebRequest;

import com.imooc.security.core.properties.contsant.ValidateCodeTypeEnum;
import com.imooc.security.core.validate.code.ValidateCode;
import com.imooc.security.core.validate.code.ValidateCodeRepository;

public class HttpSessionValidateCodeRepository implements ValidateCodeRepository {

	public final static String VALIDATECODE_SESSIONKEY_PREFIX = "SESSION_KEY_FOR_CODE_";
	
	private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();
	
	@Override
	public void save(ServletWebRequest request, ValidateCode code, ValidateCodeTypeEnum codeType) {
		sessionStrategy.setAttribute(request, getSessionKey(codeType), code);
	}

	@Override
	public ValidateCode get(ServletWebRequest request, ValidateCodeTypeEnum codeType) {
		Object objectInSession = sessionStrategy.getAttribute(request, getSessionKey(codeType));
		if (objectInSession != null) {
			return (ValidateCode) objectInSession;
		} else {
			return null;
		}
	}

	@Override
	public void remove(ServletWebRequest request, ValidateCodeTypeEnum codeType) {
		sessionStrategy.removeAttribute(request, getSessionKey(codeType));
	}
	
	private String getSessionKey(ValidateCodeTypeEnum codeType) {
		StringBuilder buff = new StringBuilder(VALIDATECODE_SESSIONKEY_PREFIX);
		buff.append(codeType);
		return buff.toString();
	}

}
