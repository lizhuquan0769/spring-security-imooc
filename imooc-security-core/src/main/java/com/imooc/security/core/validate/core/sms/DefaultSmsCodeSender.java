package com.imooc.security.core.validate.core.sms;

public class DefaultSmsCodeSender implements SmsCodeSender {
	@Override
	public void send(String mobile, String code) {
		System.out.println(String.format("向手机%s发送验证码%s", mobile, code));
	}
}
