package com.imooc.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.imooc.service.HelloService;

public class MyConstraintValidator implements ConstraintValidator<MyConstraint, Object> {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private HelloService helloService;
	
	@Override
	public void initialize(MyConstraint anno) {
		logger.info("my validator init");
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext arg1) {
		logger.info(helloService.greeting("tom"));
		logger.info(value.toString());
		return false;
	}

}
