package com.imooc.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class TimeInterceptor implements HandlerInterceptor {

	// 无论controller是抛出异常还是正常执行完毕, 该方法都会执行
	// 但是如果@ControllerAdvice把异常处理掉了, 则afterCompletion则不会接收到异常
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("time interceptor afterCompletion start...");
		System.out.println("time interceptor afterCompletion 耗时: " + (System.currentTimeMillis() - (Long)request.getAttribute("interceptorStartTime")));
		System.out.println("time interceptor afterCompletion ex: " + ex);
		System.out.println("time interceptor afterCompletion end...");
	}

	// controller抛出异常则该方法不会被执行
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView mv)
			throws Exception {
		System.out.println("time interceptor postHandle start...");
		System.out.println("time interceptor postHandle 耗时: " + (System.currentTimeMillis() - (Long)request.getAttribute("interceptorStartTime")));
		System.out.println("time interceptor postHandle end...");
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		System.out.println("time interceptor preHandle start...");
		System.out.println("time interceptor preHandle handleClass: " + ((HandlerMethod)handler).getBean().getClass().getName());
		System.out.println("time interceptor preHandle handleMethod: " + ((HandlerMethod)handler).getMethod().getName());
		request.setAttribute("interceptorStartTime", System.currentTimeMillis());
		System.out.println("time interceptor preHandle end...");
		return true;
	}

}
