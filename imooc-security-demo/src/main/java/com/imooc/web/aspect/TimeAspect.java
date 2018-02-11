package com.imooc.web.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// 切入点， 在哪些地方起作用， 在什么时候起作用
// 增强， 执行的额外逻辑方法
//@Aspect
//@Component
public class TimeAspect {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Around("execution(* com.imooc.web.controller.UserController.*(..))")
	public Object handleControllerMethod(ProceedingJoinPoint pjp) throws Throwable {
		logger.info("time aspect start...");
		
		Object[] args = pjp.getArgs();
		for (Object arg : args) {
			logger.info("time aspect arg is：" + arg);
		}
		
		long aspectStartTime = System.currentTimeMillis();
		Object result = pjp.proceed();
		logger.info("time aspect controller result: " + result);
		logger.info("time aspect controller 耗时: " + (System.currentTimeMillis() - aspectStartTime));
		
		logger.info("time aspect end..");
		return result;
	}
}
