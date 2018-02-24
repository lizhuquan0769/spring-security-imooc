package com.imooc.security.core.properties.contsant;

public class SecurityConstants {
	
	/**
	 * 当请求需要身份认证时，默认跳转的url
	 */
	public static final String DEFAULT_UNAUTHENTICATION_URL = "/authentication/dispatch";
	
	/**
	 * 默认登录页面url
	 */
	public static final String DEFAULT_SIGNIN_PAGE_URL = "/imooc-signin.html";
	
	/**
	 * 默认注册也url
	 */
	public static final String DEFAULT_SINGUP_PAGE_URL = "/imooc-singup.html";
	
	/**
	 * 默认获取图像验证码的url
	 */
	public static final String DEFAULT_VALIDATE_CODE_URL_IMAGE = "/code/image";
	
	/**
	 * 默认获取短信验证码的url
	 */
	public static final String DEFAULT_VALIDATE_CODE_URL_SMS = "/code/sms";
	
	/**
	 * 注册处理url
	 */
	public static final String DEFAULT_SINGUP_PROCESS_URL = "/regist";
	
	/**
	 * 默认的用户名密码登录请求处理url
	 */
	public static final String DEFAULT_SIGNIN_PROCESS_URL_FORM = "/authentication/form";
	
	/**
	 * 默认的手机验证码登录请求处理url
	 */
	public static final String DEFAULT_SIGNIN_PROCESS_URL_MOBILE = "/authentication/mobile";
	
	/**
	 * 默认的openid登陆请求处理url
	 */
	public static final String DEFAULT_SIGNIN_PROCESS_URL_OPENID = "/authentication/openId";
	
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
	
	/**
	 * 社交登陆时， 传递的openId参数名
	 */
	public static final String DEFAULT_REQUEST_PARAMETER_OPENID = "openId";
	
	/**
	 * 社交登陆时，传递的providerId参数名
	 */
	public static final String DEFAULT_REQUEST_PARAMETER_PROVIDERID = "providerId";
	
	/**
	 * 默认的Social UserConnection表名的前缀
	 */
	public static final String DEFAULT_SOCIAL_USER_CONNECTION_PREFIX = "imooc_";
	
	/**
	 * 默认的session失效时重定向地址
	 */
	public static final String DEFAULT_INVALID_SESSION_REDIRECT_URL = "/imooc-session-invalid.html";
	
	/**
	 * 默认同一用户最多产生的session数
	 */
	public static final int DEFAULT_MAX_SESSIOIN_PRE_USER = 1;
	
	/**
	 * 默认如果超出最大session限制, 是否阻止用户登陆
	 */
	public static final boolean DEFAULT_MAX_SESSION_PREVENTS_LOGIN = false;
	
	/**
	 * 默认退出登陆URL
	 */
	public static final String DEFAULT_SIGNOUT_URL = "/signout";
	
	/**
	 * 默认成功退出登陆URL
	 */
	public static final String DEFAULT_SIGNOUT_SUCCESS_URL = "/imooc-signout.html";
	
	/**
	 * 默认退出登陆后删除的cookies
	 */
	public static final String[] DEFAULT_SIGNOUT_DELETE_COOKIES = new String[] {"JSESSIONID", "SESSIONID"};
}
