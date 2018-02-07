package com.imooc.security.core.validate.code.image;

import java.io.IOException;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import com.imooc.security.core.validate.code.AbstractValidateCodeProcessor;

@Component
public class ImageCodeProcessor extends AbstractValidateCodeProcessor<ImageCode, ImageCodeGenerator>{

	@Autowired
	private ImageCodeGenerator imageCodeGenerator;
	
	@Override
	protected ImageCodeGenerator getValidateCodeGenerator() {
		return imageCodeGenerator;
	}

	@Override
	protected void send(ServletWebRequest request, ImageCode validateCode) throws IOException {
		request.getResponse().addHeader("Content-Type", "image/jpeg");
		ImageIO.write(validateCode.getImage(), "JPEG", request.getResponse().getOutputStream());
	}

}
