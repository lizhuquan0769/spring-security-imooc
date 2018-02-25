package com.imooc.web.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import com.fasterxml.jackson.annotation.JsonView;
import com.imooc.dto.User;
import com.imooc.dto.User.UserDetailView;
import com.imooc.dto.User.UserSimpleView;
import com.imooc.dto.UserQueryCondition;
import com.imooc.security.core.properties.SecurityProperties;
import com.imooc.security.core.social.AppSignupUtils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
public class UserController {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private SecurityProperties securityProperties;
	
	@Autowired
	private ProviderSignInUtils providerSignInUtils;
	
	@Autowired
	private AppSignupUtils appSignUpUtils;
	
	@PostMapping("#{globalSecurityProperties.browser.signupProcessUrl}")
	public void regist(User user, HttpServletRequest request) {
		// 不管是注册用户还是绑定用户，都会拿到一个用户唯一标识
		String userId = user.getUsername();
		
		logger.info(request.getParameter("type"));
		
		// 浏览器注册用providerSignInUtils
//		providerSignInUtils.doPostSignUp(userId, new ServletWebRequest(request));
		// app注册用AppSignUpUtils
		appSignUpUtils.doPostSignUp(new ServletWebRequest(request), userId);
	}
	
	@GetMapping("/users")
	@JsonView(UserSimpleView.class)
	@ApiOperation(value = "用户查询")
	public List<User> query(UserQueryCondition condition, @PageableDefault(page=2, size=17, sort="username", direction = Sort.Direction.ASC) Pageable pageable) {
		logger.info(ReflectionToStringBuilder.toString(condition, ToStringStyle.MULTI_LINE_STYLE));
		logger.info(pageable.getPageSize() + "");
		logger.info(pageable.getPageNumber() + "");
		logger.info(pageable.getSort().toString());
		List<User> users = new ArrayList<>();
		users.add(new User());
		users.add(new User());
		users.add(new User());
		return users;
	}
	
	// url参数id必须为正则表达式\d+
	@GetMapping("/users/{id:\\d+}")
	@JsonView(UserDetailView.class)
	public User getInfo(@ApiParam(value = "用户ID") @PathVariable String id) {
		// block 1：正常返回结果
		logger.info("user controller getInfo process ...");
		User user = new User();
		user.setUsername("tom");
		user.setPassword("tom-password");
		return user;
		
		// block 2：抛出UserNotExistException
		// @ControllerAdvice里定义了该异常的处理， 所以Interceptor里的afterCompletion方法接受不到该异常
//		throw new UserNotExistException("1");
		
		// block3：抛出RuntimeException
		// @ControllerAdvice里没定义该异常的处理， 所以Interceptor里的afterCompletion方法接受到该异常
//		throw new RuntimeException("user user not exist");
	}
	
	@PostMapping("/users")
	@JsonView(UserDetailView.class)
	public User create(@Valid @RequestBody User user, BindingResult errors) {
		if (errors.hasErrors()) {
			errors.getAllErrors().stream().forEach(error -> System.out.println(error.getDefaultMessage()));
		}
		logger.info(user.getUsername());
		logger.info(user.getPassword());
		logger.info(user.getBirthday().toString());
		user.setId("1");
		return user;
	}
	
	@PutMapping("/users/{id:\\d+}")
	@JsonView(UserDetailView.class)
	public User update(@Valid @RequestBody User user, BindingResult errors) {
		if (errors.hasErrors()) {
			errors.getAllErrors().stream().forEach(error -> {
				FieldError fieldError = (FieldError)error;
				String message = fieldError.getField() + " => " + fieldError.getDefaultMessage();
				logger.info(message);
			});
		}
		logger.info(user.getUsername());
		logger.info(user.getPassword());
		logger.info(user.getBirthday().toString());
		return user;
	}
	
	@DeleteMapping("/users/{id:\\d+}")
	public void delete(@PathVariable String id) {
		logger.info(id);
	}
	
	@GetMapping("/users/me")
	public Object getCurrentUser(Authentication user, HttpServletRequest request) throws ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, SignatureException, IllegalArgumentException, UnsupportedEncodingException {
		
		String header = request.getHeader("Authorization");
		String token = StringUtils.substringAfter(header, "bearer ");
		
		// 获取jwtToken的附加信息
		Claims claims = Jwts.parser().setSigningKey(securityProperties.getOauth2().getSigningKey().getBytes("UTF-8"))
			.parseClaimsJws(token).getBody();
		
		String company = (String) claims.get("company");
		System.out.println("-->" + company);
		
		return user;
	}
	
}
