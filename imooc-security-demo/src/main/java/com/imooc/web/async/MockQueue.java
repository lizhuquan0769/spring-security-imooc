package com.imooc.web.async;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class MockQueue {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	private String placeOrder;
	private String completeOrder;
	public String getPlaceOrder() {
		return placeOrder;
	}
	public void setPlaceOrder(String placeOrder) {
		new Thread(() -> {
			this.placeOrder = placeOrder;
			logger.info("调用方正在发送消息通知订单服务处理，订单号：" + placeOrder );
			try {
				Thread.sleep(2000L);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			this.completeOrder = placeOrder;
			logger.info("调用方接收到订单服务的处理成功的结果，订单号：" + placeOrder);
		}).start();
	}
	public String getCompleteOrder() {
		return completeOrder;
	}
	public void setCompleteOrder(String completeOrder) {
		this.completeOrder = completeOrder;
	}
}
