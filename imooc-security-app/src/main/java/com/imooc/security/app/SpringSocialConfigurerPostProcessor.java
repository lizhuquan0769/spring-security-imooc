package com.imooc.security.app;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import com.imooc.security.core.social.ImoocSpringSocialConfigurer;

/**
 * 所有bean初始化前后都会经过这个类的方法
 * @author Administrator
 *
 */
@Component
public class SpringSocialConfigurerPostProcessor implements BeanPostProcessor {

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		if (StringUtils.equals(beanName, "imoocSocialSecurityConfig")) {
			ImoocSpringSocialConfigurer configurer = (ImoocSpringSocialConfigurer) bean;
			//三方用户openid不存在于user_connection表时，重定向的路径
			configurer.signupUrl("/social/signup");
			return configurer;
		}
		return bean;
	}

}
