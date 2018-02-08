package com.imooc.security.core.properties.contsant;

public class SecurityConstants {
	
	/**
	 * 当请求需要身份认证时，默认跳转的url
	 */
	public static final String DEFAULT_UNAUTHENTICATION_URL = "/authentication/dispatch";
	
	/**
	 * 默认登录页面url
	 */
	public static final String DEFAULT_LOGIN_PAGE_URL = "/imooc-signin.html";
	
	/**
	 * 默认获取图像验证码的url
	 */
	public static final String DEFAULT_VALIDATE_CODE_IMAGE_URL = "/code/image";
	
	/**
	 * 默认获取短信验证码的url
	 */
	public static final String DEFAULT_VALIDATE_CODE_SMS_URL = "/code/sms";
	
	/**
	 * 默认的用户名密码登录请求处理url
	 */
	public static final String DEFAULT_LOGIN_PROCESS_URL_FORM = "/authentication/form";
	
	/**
	 * 默认的手机验证码登录请求处理url
	 */
	public static final String DEFAULT_LOGIN_PROCESS_URL_MOBILE = "/authentication/mobile";
	
	/**
	 * 验证图片验证码时，http请求中默认的携带图片验证码信息的参数的名称
	 */
	public static final String DEFAULT_REQUEST_PARAMETER_IMAGECODE = "imageCode";
	
	/**
	 * 验证短信验证码时，http请求中默认的携带短信验证码信息的参数的名称
	 */
	public static final String DEFAULT_REQUEST_PARAMETER_SMSCODE= "smsCode";
	
	/**
	 * 发送短信验证码 或 验证短信验证码时，传递手机号的参数的名称
	 */
	public static final String DEFAULT_REQUEST_PARAMETER_MOBILE = "mobile";
	
}
