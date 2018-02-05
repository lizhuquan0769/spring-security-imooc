package com.imooc.web.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

// 切入点， 在哪些地方起作用， 在什么时候起作用
// 增强， 执行的额外逻辑方法
//@Aspect
//@Component
public class TimeAspect {
	
	@Around("execution(* com.imooc.web.controller.UserController.*(..))")
	public Object handleControllerMethod(ProceedingJoinPoint pjp) throws Throwable {
		System.out.println("time aspect start...");
		
		Object[] args = pjp.getArgs();
		for (Object arg : args) {
			System.out.println("time aspect arg is：" + arg);
		}
		
		long aspectStartTime = System.currentTimeMillis();
		Object result = pjp.proceed();
		System.out.println("time aspect controller result: " + result);
		System.out.println("time aspect controller 耗时: " + (System.currentTimeMillis() - aspectStartTime));
		
		System.out.println("time aspect end..");
		return result;
	}
}
