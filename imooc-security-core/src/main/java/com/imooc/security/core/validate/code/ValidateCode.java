package com.imooc.security.core.validate.code;

import java.time.LocalDateTime;

/**
 * 所有验证码实体类的父类
 * @author lizhuquan
 *
 */
public class ValidateCode {
	protected String code;
	
	protected LocalDateTime expireTime;
	
	public ValidateCode(String code, int expireIn) {
		this.code = code;
		this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
	}
	
	public ValidateCode(String code, LocalDateTime expireTime) {
		this.code = code;
		this.expireTime = expireTime;
	}
	
	public boolean isExpired() {
		return expireTime.isBefore(LocalDateTime.now());
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public LocalDateTime getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(LocalDateTime expireTime) {
		this.expireTime = expireTime;
	}

	@Override
	public String toString() {
		return "ValidateCode [code=" + code + ", expireTime=" + expireTime + "]";
	}
}
