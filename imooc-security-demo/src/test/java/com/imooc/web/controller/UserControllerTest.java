package com.imooc.web.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class) //用SpringRunner来跑测试用例
@SpringBootTest //整个类是测试用例
public class UserControllerTest {
	@Autowired
	private WebApplicationContext wac;
	
	private MockMvc mockMvc;
	
	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}
	
	@Test
	public void whenQuerySuccess() throws Exception {
		String result = mockMvc.perform(get("/users")
					// UserQueryCondition对象参数
					.param("username", "jojo")
					.param("age", "18")
					.param("ageTo", "60")
					.param("xxx", "yyy")
					// Pageable对象参数
					.param("size", "15")
					.param("page", "3")
					.param("sort", "age,desc")
					// 请求的content-type
					.contentType(MediaType.APPLICATION_JSON_UTF8))
				// 期望返回200状态码
				.andExpect(status().isOk())
				// 期望返回的json数组的长度为3
				// JsonPath地址：https://github.com/json-path/JsonPath
				.andExpect(jsonPath("$.length()").value(3))
				.andReturn().getResponse().getContentAsString();
		System.out.println(result);
	}
	
	@Test
	public void whenGetInfoSuccess() throws Exception {
		String result = mockMvc.perform(get("/users/1")
					.contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.username").value("tom"))
				.andReturn().getResponse().getContentAsString();
		System.out.println(result);
	}
	
	@Test
	public void whenGetInfoFail() throws Exception {
		mockMvc.perform(get("/users/a")
					.contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().is4xxClientError());
	}
	
	@Test
	public void whenCreateSuccess() throws Exception {
		Date date = new Date();
		System.out.println("请求date: " + date);
		String content = "{\"username\":\"tom\", \"password\":null, \"birthday\": " + date.getTime() + "}";
		System.out.println("请求json: " + content);
		String result = mockMvc.perform(post("/users")
					.contentType(MediaType.APPLICATION_JSON_UTF8)
					.content(content))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value("1"))
				.andReturn().getResponse().getContentAsString();
		System.out.println("返回json: " + result);
	}
	
	@Test
	public void whneUpdateSuccess() throws Exception {
		Date date = new Date(System.currentTimeMillis() + 1000 * 3600 * 24 * 365);
		System.out.println("请求date: " + date);
		String content = "{\"id\":\"1\", \"username\":\"tom\", \"password\":null, \"birthday\": " + date.getTime() + "}";
		System.out.println("请求json: " + content);
		String result = mockMvc.perform(put("/users/1")
					.contentType(MediaType.APPLICATION_JSON_UTF8)
					.content(content))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value("1"))
				.andReturn().getResponse().getContentAsString();
		System.out.println("返回json: " + result);
	}
	
	@Test
	public void whenDeleteSuccess() throws Exception {
		mockMvc.perform(delete("/users/1")
					.contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk());
	}
}
