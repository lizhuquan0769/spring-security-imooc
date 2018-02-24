package com.imooc.security.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.imooc.security.core.properties.SecurityProperties;
import com.imooc.security.core.validate.code.image.DefaultImageCodeGenerator;
import com.imooc.security.core.validate.code.image.ImageCodeGenerator;
import com.imooc.security.core.validate.code.sms.DefaultSmsCodeGenerator;
import com.imooc.security.core.validate.code.sms.DefaultSmsCodeSender;
import com.imooc.security.core.validate.code.sms.SmsCodeGenerator;
import com.imooc.security.core.validate.code.sms.SmsCodeSender;


@Configuration
public class CoreSecurityConfig {
	
	@Autowired
	private SecurityProperties securityProperties;
	
	@Bean
	@ConditionalOnMissingBean(value = ImageCodeGenerator.class)
	public ImageCodeGenerator imageCodeGenerator() {
		DefaultImageCodeGenerator imageCodeGenerator = new DefaultImageCodeGenerator();
		imageCodeGenerator.setSecurityProperties(securityProperties);
		return imageCodeGenerator;
	}
	
	@Bean
	@ConditionalOnMissingBean(value = SmsCodeGenerator.class)
	public SmsCodeGenerator smsCodeGenerator() {
		DefaultSmsCodeGenerator smsCodeGenerator = new DefaultSmsCodeGenerator();
		smsCodeGenerator.setSecurityProperties(securityProperties);
		return smsCodeGenerator;
	}
	
	@Bean
	@ConditionalOnMissingBean(value = SmsCodeSender.class)
	public SmsCodeSender smsCodeSender() {
		DefaultSmsCodeSender smsCodeSender = new DefaultSmsCodeSender();
		return smsCodeSender;
	}
}
