package com.imooc.web.async;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class QueueListener implements ApplicationListener<ContextRefreshedEvent> {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private MockQueue queue;
	
	@Autowired
	private DeferredResultHolder deferredResultHolder;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		new Thread(() -> {
			while (true) {
				if (StringUtils.isNotBlank(queue.getCompleteOrder())) {
					String completeOrder = queue.getCompleteOrder();
					
					logger.info("监听到调用方已成功接收订单服务的处理结果，正在通知客户端显示，订单号：" + completeOrder);
					deferredResultHolder.getMap().get(completeOrder).setResult("place order success：" + completeOrder);
					
					queue.setCompleteOrder(null);
				} else {
					try {
						Thread.sleep(100L);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}
	
}
