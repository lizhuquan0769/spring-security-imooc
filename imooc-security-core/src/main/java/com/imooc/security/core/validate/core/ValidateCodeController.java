package com.imooc.security.core.validate.core;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import com.google.code.kaptcha.impl.DefaultKaptcha;

@RestController
public class ValidateCodeController {
	
	public static final String SESSION_KEY = "SESSION_KEY_IMAGE_CODE";
	
	private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();
	
	@Autowired
	private DefaultKaptcha kaptcha;
	
	@GetMapping("/code/image")
	public void createCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
		System.out.println("上一个验证码：" + sessionStrategy.getAttribute(new ServletWebRequest(request), SESSION_KEY));
		
		ImageCode imageCode = createImageCode(request);
		
		sessionStrategy.setAttribute(new ServletWebRequest(request), SESSION_KEY, imageCode);
		
		ImageIO.write(imageCode.getImage(), "JPEG", response.getOutputStream());
	}

	private ImageCode createImageCode(HttpServletRequest request) {
		String captchaText = kaptcha.createText();
		BufferedImage captchaImage = kaptcha.createImage(captchaText);
		return new ImageCode(captchaImage, captchaText, 60);
	}
}
