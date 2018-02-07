package com.imooc.security.core.validate.code.sms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import com.imooc.security.core.validate.code.AbstractValidateCodeProcessor;

@Component
public class SmsCodeProcessor extends AbstractValidateCodeProcessor<SmsCode, SmsCodeGenerator>{

	@Autowired
	private SmsCodeGenerator smsCodeGenerator;
	
	@Autowired
	private SmsCodeSender smsCodeSender;
	
	@Override
	protected SmsCodeGenerator getValidateCodeGenerator() {
		return smsCodeGenerator;
	}

	@Override
	protected void send(ServletWebRequest request, SmsCode validateCode) throws Exception {
		String mobile = ServletRequestUtils.getStringParameter(request.getRequest(), "mobile");
		smsCodeSender.send(mobile, validateCode.getCode());
	}

}
