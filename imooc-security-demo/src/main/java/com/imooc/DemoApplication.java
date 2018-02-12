package com.imooc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.View;

import com.imooc.security.core.social.ImoocConnecStatusView;
import com.imooc.social.qq.DemoConnectedView;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class DemoApplication {
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	
	// 覆盖core包中配置的Bean
	@Bean(name = {"connect/qqConnect", "connect/qqConnected"})
	public View qqConnectedView() {
		return new DemoConnectedView();
	}
	
	// 覆盖core包中配置的Bean
	@Bean("connect/status")
	public View connectStatusView() {
		return new ImoocConnecStatusView();
	}
	
}
