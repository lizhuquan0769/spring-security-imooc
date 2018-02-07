package com.imooc.security.core.validate.code;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import com.imooc.security.core.validate.code.image.ImageCodeProcessor;
import com.imooc.security.core.validate.code.sms.SmsCodeProcessor;

@RestController
public class ValidateCodeController {
	
	private ImageCodeProcessor imageCodeProcessor;
	
	private SmsCodeProcessor smsCodeProcessor;
	
	
	@GetMapping("${imooc.security.browser.image-code-uri}")
	public void createCode(HttpServletRequest request, HttpServletResponse response) throws Exception {
		imageCodeProcessor.process(new ServletWebRequest(request, response));
	}
	
	@GetMapping(value = "${imooc.security.browser.sms-code-uri}", params = {"mobile"})
	public void createSmsCode(HttpServletRequest request, HttpServletResponse response) throws Exception {
		smsCodeProcessor.process(new ServletWebRequest(request, response));
	}

	
}
