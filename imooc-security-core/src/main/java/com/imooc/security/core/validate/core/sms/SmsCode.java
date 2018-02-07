package com.imooc.security.core.validate.core.sms;

import java.time.LocalDateTime;

import com.imooc.security.core.validate.core.ValidateCode;

public class SmsCode extends ValidateCode {

	public SmsCode(String code, int expireIn) {
		super(code, expireIn);
	}
	
	public SmsCode(String code, LocalDateTime expireTime) {
		super(code, expireTime);
	}

	@Override
	public String toString() {
		return "SmsCode [code=" + code + ", expireTime=" + expireTime + "]";
	}
}
