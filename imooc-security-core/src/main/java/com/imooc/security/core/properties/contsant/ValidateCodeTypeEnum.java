package com.imooc.security.core.properties.contsant;

public enum ValidateCodeTypeEnum {
	SMS {
		@Override
		public String getParamNameOnValidate() {
			return "smsCode";
		}
	},
	IMAGE {
		@Override
		public String getParamNameOnValidate() {
			return "imageCode";
		}
	};

	public abstract String getParamNameOnValidate();
}
