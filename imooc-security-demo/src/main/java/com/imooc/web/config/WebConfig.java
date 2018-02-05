package com.imooc.web.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.imooc.web.filter.TimeFilter;
import com.imooc.web.interceptor.TimeInterceptor;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
	
//	@Bean
//	public FilterRegistrationBean timeFilter() {
//		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
//		
//		TimeFilter timeFilter = new TimeFilter();
//		registrationBean.setFilter(timeFilter);
//		
//		List<String> urls = new ArrayList<>();
//		urls.add("/*");
//		registrationBean.setUrlPatterns(urls);
//		
//		return registrationBean;
//	}
	
//	@Autowired
//	private TimeInterceptor timeInterceptor;
//	@Override
//	public void addInterceptors(InterceptorRegistry registry) {
//		super.addInterceptors(registry);
//		registry.addInterceptor(timeInterceptor);
//	}
	
//	@Override
//	public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
//		configurer.registerCallableInterceptors(interceptors); // 设置Callable<T>形式的拦截器
//		configurer.registerDeferredResultInterceptors(interceptors); // 设置DeferredResult<T>形式的拦截器
//		configurer.setDefaultTimeout(timeout);	// 设置处理线程的默认超时时间
//		configurer.setTaskExecutor(taskExecutor); // 设置处理线程的线程池
//	}
}
