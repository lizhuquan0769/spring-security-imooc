package com.imooc.security.core.validate.code.sms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultSmsCodeSender implements SmsCodeSender {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Override
	public void send(String mobile, String code) {
		logger.info(String.format("向手机%s发送验证码%s", mobile, code));
	}
}
