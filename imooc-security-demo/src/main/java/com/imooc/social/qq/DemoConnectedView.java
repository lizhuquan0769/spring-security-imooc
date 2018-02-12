package com.imooc.social.qq;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.AbstractView;

public class DemoConnectedView extends AbstractView {

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setContentType("text/plain;charset=UTF-8");
		if (model.get("connections") == null) {
			response.getWriter().write("demo 解绑成功");
		} else {
			response.getWriter().write("demo 绑定成功");
		}
		
	}

}
