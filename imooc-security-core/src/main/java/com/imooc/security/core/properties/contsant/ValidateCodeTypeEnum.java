package com.imooc.security.core.properties.contsant;

public enum ValidateCodeTypeEnum {
	IMAGE {
		@Override
		public String getParameterNameOnValidate() {
			return Constants.DEFAULT_PARAMEGERNAME_IMAGECODE;
		}
	},
	SMS {
		@Override
		public String getParameterNameOnValidate() {
			return Constants.DEFAULT_PARAMEGERNAME_SMSCODE;
		}
	};

	public abstract String getParameterNameOnValidate();
}
