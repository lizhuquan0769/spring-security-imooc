package com.imooc.security.core.validate.code;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.context.request.ServletWebRequest;

import com.imooc.security.core.properties.contsant.ValidateCodeTypeEnum;

public class RedisValidateCodeRepository implements ValidateCodeRepository {

	public static final String DEVICEID_HEADER_NAME = "deviceId";
	
	public static final String VALIDATECODE_REDISKEY_PREFIX = "code:";
	
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	
	@Override
	public void save(ServletWebRequest request, ValidateCode code, ValidateCodeTypeEnum codeType) {
		redisTemplate.opsForValue().set(getRedisKey(request, codeType), code, 30, TimeUnit.MINUTES);
	}

	@Override
	public ValidateCode get(ServletWebRequest request, ValidateCodeTypeEnum codeType) {
		Object codeInRedis = redisTemplate.opsForValue().get(getRedisKey(request, codeType));
		if (codeInRedis != null) {
			return (ValidateCode) codeInRedis;
		} else {
			return null;
		}
	}

	@Override
	public void remove(ServletWebRequest request, ValidateCodeTypeEnum codeType) {
		redisTemplate.delete(getRedisKey(request, codeType));
	}
	
	private String getRedisKey(ServletWebRequest request, ValidateCodeTypeEnum codeType) {
		String deviceId = getDeviceId(request);
		StringBuilder buff = new StringBuilder(VALIDATECODE_REDISKEY_PREFIX);
		buff.append(codeType).append(":").append(deviceId);
		return buff.toString();
	}
	
	private String getDeviceId(ServletWebRequest request) {
		String deviceId = request.getHeader(DEVICEID_HEADER_NAME);
		if (deviceId == null) {
			throw new ValidateCodeException("缺少deviceId");
		}
		return deviceId;
	}

}
