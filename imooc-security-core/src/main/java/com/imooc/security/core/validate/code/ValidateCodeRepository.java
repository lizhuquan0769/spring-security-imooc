package com.imooc.security.core.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

import com.imooc.security.core.properties.contsant.ValidateCodeTypeEnum;

public interface ValidateCodeRepository {

	void save(ServletWebRequest request, ValidateCode code, ValidateCodeTypeEnum codeType);
	
	ValidateCode get(ServletWebRequest request, ValidateCodeTypeEnum codeType);
	
	void remove(ServletWebRequest request, ValidateCodeTypeEnum codeType);
}
