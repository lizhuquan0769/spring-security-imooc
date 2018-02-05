package com.imooc.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

// 使用注解@Component也能把filter注入到web环境, 也可以通过创建FilterRegistratorBean注入
//@Component
public class TimeFilter implements Filter {

	@Override
	public void destroy() {
		System.out.println("time filter destory");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("time filter start...");
		System.out.println("time filter request url" + ((HttpServletRequest)request).getRequestURL());
		long filterStartTime = System.currentTimeMillis();
		chain.doFilter(request, response);
		System.out.println("time filter 耗时：" + (System.currentTimeMillis() - filterStartTime));
		System.out.println("time filter end...");
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		System.out.println("time filter init");
	}

}
