package com.imooc.security.core.properties.contsant;

public enum ValidateCodeTypeEnum {
	IMAGE {
		@Override
		public String getParameterNameOnValidate() {
			return SecurityConstants.DEFAULT_REQUEST_PARAMETER_IMAGECODE;
		}
	},
	SMS {
		@Override
		public String getParameterNameOnValidate() {
			return SecurityConstants.DEFAULT_REQUEST_PARAMETER_SMSCODE;
		}
	};

	public abstract String getParameterNameOnValidate();
}
