package com.imooc.security;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.stereotype.Component;

@Component
public class DemoConnectionSignUp implements ConnectionSignUp {

	@Override
	public String execute(Connection<?> connection) {
		// 根据社交用户信息默认创建用户并返回用户业务唯一标识 
		return connection.getDisplayName();
	}

}
