package com.imooc.security.core.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

import com.imooc.security.core.properties.contsant.ValidateCodeTypeEnum;
import com.imooc.security.core.validate.code.image.ImageCode;
import com.imooc.security.core.validate.code.sms.SmsCode;

public class ValidateCodeHttpUtils {
	
	public final static String VALIDATECODE_SESSIONKEY_PREFIX = "SESSION_KEY_FOR_CODE_";
	
	public static String getValidateCodeSessionKey(ValidateCodeTypeEnum validateCodeType) {
		String sessionKey = null;
		switch (validateCodeType) {
		case IMAGE:
			sessionKey = buildSessionKey(ValidateCodeTypeEnum.IMAGE);
			break;
		case SMS:
			sessionKey =  buildSessionKey(ValidateCodeTypeEnum.SMS);
			break;
		default:
			sessionKey = buildSessionKey(ValidateCodeTypeEnum.IMAGE);
			break;
		}
		return sessionKey;
	}
	
	public static String getValidateCodeSessionKey(ValidateCode validateCode) {
		String sessionKey = null;
		if (validateCode instanceof ImageCode) {
			sessionKey = buildSessionKey(ValidateCodeTypeEnum.IMAGE);
		} else if (validateCode instanceof SmsCode) {
			sessionKey =  buildSessionKey(ValidateCodeTypeEnum.SMS);
		} else {
			sessionKey = buildSessionKey(ValidateCodeTypeEnum.IMAGE);
		}
		return sessionKey;
	}
	
	public static String getValidateCodeInRequest(ServletWebRequest request, ValidateCodeTypeEnum validateCodeType) {
		String codeInRequest = request.getParameter(validateCodeType.getParameterNameOnValidate());
		return codeInRequest;
	}
	
	private static String buildSessionKey(ValidateCodeTypeEnum validateCodeType) {
		return VALIDATECODE_SESSIONKEY_PREFIX + validateCodeType.toString().toUpperCase();
	}
}
