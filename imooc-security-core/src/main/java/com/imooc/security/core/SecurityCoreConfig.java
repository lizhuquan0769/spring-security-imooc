package com.imooc.security.core;

import java.util.Properties;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import com.imooc.security.core.properties.SecurityProperties;


@Configuration
@EnableConfigurationProperties(SecurityProperties.class)
public class SecurityCoreConfig {
	
	@Bean
	public DefaultKaptcha kaptchaBean() {
		DefaultKaptcha kaptcha = new DefaultKaptcha();
		Properties prop = new Properties();
		prop.setProperty("kaptcha.border", "yes");
		prop.setProperty("kaptcha.border.color", "105,179,90");
		prop.setProperty("kaptcha.textproducer.font.color", "blue");
		prop.setProperty("kaptcha.image.width", "125");
		prop.setProperty("kaptcha.image.height", "45");
		prop.setProperty("kaptcha.session.key", "code");
		prop.setProperty("kaptcha.textproducer.char.length", "4");
		prop.setProperty("kaptcha.textproducer.font.names", "宋体,楷体,微软雅黑");
		Config conf = new Config(prop);
		kaptcha.setConfig(conf);
		return kaptcha;
	}
}
