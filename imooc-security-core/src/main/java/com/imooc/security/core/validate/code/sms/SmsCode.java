package com.imooc.security.core.validate.code.sms;

import java.time.LocalDateTime;

import com.imooc.security.core.validate.code.ValidateCode;

public class SmsCode extends ValidateCode {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
