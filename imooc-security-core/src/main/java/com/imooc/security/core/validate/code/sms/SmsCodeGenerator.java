package com.imooc.security.core.validate.code.sms;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.web.context.request.ServletWebRequest;

import com.imooc.security.core.properties.SecurityProperties;
import com.imooc.security.core.validate.code.ValidateCodeGenerator;

public class SmsCodeGenerator implements ValidateCodeGenerator {

	private SecurityProperties securityProperties;
	
	@Override
	public SmsCode generate(ServletWebRequest request) {
		String code = RandomStringUtils.randomNumeric(securityProperties.getCode().getSms().getLength());
		return new SmsCode(code, securityProperties.getCode().getSms().getExpireIn());
	}

	public void setSecurityProperties(SecurityProperties securityProperties) {
		this.securityProperties = securityProperties;
	}
}
