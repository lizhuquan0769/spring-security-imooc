package com.imooc.security.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.imooc.security.core.properties.ImoocSecurityProperties;
import com.imooc.security.core.validate.code.image.ImageCodeGenerator;
import com.imooc.security.core.validate.code.sms.DefaultSmsCodeSender;
import com.imooc.security.core.validate.code.sms.SmsCodeGenerator;


@Configuration
public class CoreSecurityConfig {
	
	@Autowired
	private ImoocSecurityProperties securityProperties;
	
	@Bean
	@ConditionalOnMissingBean(value = ImageCodeGenerator.class)
	public ImageCodeGenerator imageCodeGenerator() {
		ImageCodeGenerator imageCodeGenerator = new ImageCodeGenerator();
		imageCodeGenerator.setSecurityProperties(securityProperties);
		return imageCodeGenerator;
	}
	
	@Bean
	@ConditionalOnMissingBean(value = SmsCodeGenerator.class)
	public SmsCodeGenerator smsCodeGenerator() {
		SmsCodeGenerator smsCodeGenerator = new SmsCodeGenerator();
		smsCodeGenerator.setSecurityProperties(securityProperties);
		return smsCodeGenerator;
	}
	
	@Bean
	@ConditionalOnMissingBean(value = DefaultSmsCodeSender.class)
	public DefaultSmsCodeSender smsCodeSender() {
		DefaultSmsCodeSender smsCodeSender = new DefaultSmsCodeSender();
		return smsCodeSender;
	}
}
