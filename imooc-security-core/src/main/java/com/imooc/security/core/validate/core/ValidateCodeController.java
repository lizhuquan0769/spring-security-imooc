package com.imooc.security.core.validate.core;

import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import com.imooc.security.core.validate.core.image.ImageCode;
import com.imooc.security.core.validate.core.image.ImageCodeGenerator;
import com.imooc.security.core.validate.core.sms.SmsCode;
import com.imooc.security.core.validate.core.sms.SmsCodeGenerator;
import com.imooc.security.core.validate.core.sms.SmsCodeSender;

@RestController
public class ValidateCodeController {
	
	public static final String SESSION_KEY = "SESSION_KEY_IMAGE_CODE";
	
	private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();
	
//	@Autowired
//	private DefaultKaptcha kaptcha;
	
//	@Autowired
//	private SecurityProperties securityProperties;
	
	@Autowired
	private ImageCodeGenerator imageCodeGenerator;
	
	@Autowired
	private SmsCodeGenerator smsCodeGenerator;
	
	@Autowired
	private SmsCodeSender smsCodeSender;
	
	@GetMapping("${imooc.security.browser.image-code-uri}")
	public void createCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ImageCode imageCode = imageCodeGenerator.generate(request);
		sessionStrategy.setAttribute(new ServletWebRequest(request), SESSION_KEY, imageCode);
		
		response.addHeader("Content-Type", "image/jpeg");
		ImageIO.write(imageCode.getImage(), "JPEG", response.getOutputStream());
	}
	
	@GetMapping(value = "${imooc.security.browser.sms-code-uri}", params = {"mobile"})
	public void createSmsCode(HttpServletRequest request, HttpServletResponse response) throws ServletRequestBindingException {
		String mobile = ServletRequestUtils.getStringParameter(request, "mobile");
		SmsCode smsCode = smsCodeGenerator.generate(request);
		sessionStrategy.setAttribute(new ServletWebRequest(request), SESSION_KEY, smsCode);
		
		smsCodeSender.send(mobile, smsCode.getCode());
	}

	
}
