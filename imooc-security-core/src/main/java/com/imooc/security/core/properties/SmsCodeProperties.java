package com.imooc.security.core.properties;

public class SmsCodeProperties {
	private int length = 4;
	
	private int expireIn = 300;

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getExpireIn() {
		return expireIn;
	}

	public void setExpireIn(int expireIn) {
		this.expireIn = expireIn;
	}
}
