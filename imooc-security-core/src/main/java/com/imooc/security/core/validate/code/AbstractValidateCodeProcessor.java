package com.imooc.security.core.validate.code;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.ServletWebRequest;

import com.imooc.security.core.properties.contsant.ValidateCodeTypeEnum;
import com.imooc.security.core.validate.code.image.ImageCode;
import com.imooc.security.core.validate.code.sms.SmsCode;

public abstract class AbstractValidateCodeProcessor<C extends ValidateCode,G extends ValidateCodeGenerator> implements ValidateCodeProcessor {
	
	@Autowired
	private ValidateCodeRepository validateCodeRepository;
	
	@Override
	public void process(ServletWebRequest request) throws Exception {
		C validateCode = generate(request);
		save(request, validateCode);
		send(request, validateCode);
	}
	
	private void save(ServletWebRequest request, C validateCode) {
		ValidateCode codeToSave = new ValidateCode(validateCode.getCode(), validateCode.getExpireTime());
		validateCodeRepository.save(request, codeToSave, getValidateCodeType(validateCode));
	}

	@SuppressWarnings("unchecked")
	private C generate(ServletWebRequest request) throws Exception {
		return (C) getValidateCodeGenerator().generate(request);
	}
	
	private ValidateCodeTypeEnum getValidateCodeType(ValidateCode validateCode) {
		if (validateCode instanceof ImageCode) {
			return ValidateCodeTypeEnum.IMAGE;
		} else if (validateCode instanceof SmsCode) {
			return ValidateCodeTypeEnum.SMS;
		} else {
			throw new ValidateCodeException("不支持的validateCode类型" + validateCode.getClass().getCanonicalName());
		}
	}

	protected abstract G getValidateCodeGenerator();
	
	protected abstract void send(ServletWebRequest request, C validateCode) throws Exception;
}
