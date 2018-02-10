package com.imooc.security.browser.userdetails;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.security.SocialUser;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Component;

@Component
public class MyUserDetailsService implements UserDetailsService, SocialUserDetailsService {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		String password = "123456";
		// 根据查找到的用户信息判断用户是否被冻结
		logger.info("登陆用户名：" + username + "登陆成功");
		return new User(username, passwordEncoder.encode(password), true, true, true, true, AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
	}

	@Override
	public SocialUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException {
		String password = "123456";
		// 根据查找到的用户信息判断用户是否被冻结
		logger.info("登陆用户ID：" + userId + "登陆成功");
		return new SocialUser(userId, passwordEncoder.encode(password), true, true, true, true, AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
	}

}
