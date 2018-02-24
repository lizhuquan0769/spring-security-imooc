package com.imooc.security.core;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class CoreSecurityCustomiableBeanConfig {
	
	@Bean
	@ConditionalOnMissingBean(value = PasswordEncoder.class)
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
    @ConditionalOnMissingBean(value = RedisTemplate.class)
    public RedisTemplate<?, ?> getRedisTemplate(){
		JedisConnectionFactory factory = new JedisConnectionFactory();  
	    factory.setPoolConfig(new JedisPoolConfig()); 
	    factory.afterPropertiesSet();
	    
	    RedisTemplate<?,?> template = new RedisTemplate<>();
	    template.setConnectionFactory(factory);
	    template.afterPropertiesSet();
	    
	    return template;  
    }  
}
