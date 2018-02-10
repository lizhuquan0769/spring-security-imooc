package com.imooc.security.core.validate.code;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import com.imooc.security.core.validate.code.image.ImageCodeProcessor;
import com.imooc.security.core.validate.code.sms.SmsCodeProcessor;

@RestController
public class ValidateCodeController {
	
	@Autowired
	private ImageCodeProcessor imageCodeProcessor;
	
	@Autowired
	private SmsCodeProcessor smsCodeProcessor;
	
	
	@GetMapping("#{globalSecurityProperties.browser.validateCodeUrlImage}")
	public void createCode(HttpServletRequest request, HttpServletResponse response) throws Exception {
		imageCodeProcessor.process(new ServletWebRequest(request, response));
	}
	
	@GetMapping(value = "#{globalSecurityProperties.browser.validateCodeUrlSms}", params = {"mobile"})
	public void createSmsCode(HttpServletRequest request, HttpServletResponse response) throws Exception {
		smsCodeProcessor.process(new ServletWebRequest(request, response));
	}

	
}
