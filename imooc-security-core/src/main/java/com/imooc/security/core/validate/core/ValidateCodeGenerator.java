package com.imooc.security.core.validate.core;

import javax.servlet.http.HttpServletRequest;

public interface ValidateCodeGenerator {
	ValidateCode generate(HttpServletRequest request);
}
