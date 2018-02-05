package com.imooc.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.imooc.service.HelloService;

public class MyConstraintValidator implements ConstraintValidator<MyConstraint, Object> {
	@Autowired
	private HelloService helloService;
	
	@Override
	public void initialize(MyConstraint anno) {
		System.out.println("my validator init");
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext arg1) {
		System.out.println(helloService.greeting("tom"));
		System.out.println(value);
		return false;
	}

}
