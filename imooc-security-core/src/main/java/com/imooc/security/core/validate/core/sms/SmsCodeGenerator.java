package com.imooc.security.core.validate.core.sms;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.RandomStringUtils;

import com.imooc.security.core.properties.SecurityProperties;
import com.imooc.security.core.validate.core.ValidateCodeGenerator;

public class SmsCodeGenerator implements ValidateCodeGenerator {

	private SecurityProperties securityProperties;
	
	@Override
	public SmsCode generate(HttpServletRequest request) {
		String code = RandomStringUtils.randomNumeric(securityProperties.getCode().getSms().getLength());
		return new SmsCode(code, securityProperties.getCode().getSms().getExpireIn());
	}

	public void setSecurityProperties(SecurityProperties securityProperties) {
		this.securityProperties = securityProperties;
	}
}
