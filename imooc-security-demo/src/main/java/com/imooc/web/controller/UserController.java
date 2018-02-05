package com.imooc.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.imooc.dto.User;
import com.imooc.dto.User.UserDetailView;
import com.imooc.dto.User.UserSimpleView;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import com.imooc.dto.UserQueryCondition;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@GetMapping
	@JsonView(UserSimpleView.class)
	@ApiOperation(value = "用户查询")
	public List<User> query(UserQueryCondition condition, @PageableDefault(page=2, size=17, sort="username", direction = Sort.Direction.ASC) Pageable pageable) {
		System.out.println(ReflectionToStringBuilder.toString(condition, ToStringStyle.MULTI_LINE_STYLE));
		System.out.println(pageable.getPageSize());
		System.out.println(pageable.getPageNumber());
		System.out.println(pageable.getSort());
		List<User> users = new ArrayList<>();
		users.add(new User());
		users.add(new User());
		users.add(new User());
		return users;
	}
	
	// url参数id必须为正则表达式\d+
	@GetMapping("/{id:\\d+}")
	@JsonView(UserDetailView.class)
	public User getInfo(@ApiParam(value = "用户ID") @PathVariable String id) {
		// block 1：正常返回结果
		System.out.println("user controller getInfo process ...");
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
	
	@PostMapping
	@JsonView(UserDetailView.class)
	public User create(@Valid @RequestBody User user, BindingResult errors) {
		if (errors.hasErrors()) {
			errors.getAllErrors().stream().forEach(error -> System.out.println(error.getDefaultMessage()));
		}
		System.out.println(user.getUsername());
		System.out.println(user.getPassword());
		System.out.println(user.getBirthday());
		user.setId("1");
		return user;
	}
	
	@PutMapping("/{id:\\d+}")
	@JsonView(UserDetailView.class)
	public User update(@Valid @RequestBody User user, BindingResult errors) {
		if (errors.hasErrors()) {
			errors.getAllErrors().stream().forEach(error -> {
				FieldError fieldError = (FieldError)error;
				String message = fieldError.getField() + " => " + fieldError.getDefaultMessage();
				System.out.println(message);
			});
		}
		System.out.println(user.getUsername());
		System.out.println(user.getPassword());
		System.out.println(user.getBirthday());
		return user;
	}
	
	@DeleteMapping("/{id:\\d+}")
	public void delete(@PathVariable String id) {
		System.out.println(id);
	}
	
}
