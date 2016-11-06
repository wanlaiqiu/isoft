package com.van.products.util.spring;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/** 
 * @className: SpringUtil.java<br/>
 * @classDescription: Spring手动获取实例<br/>
 * @createTime: 2015年4月25日
 * @author Van
 */

@Component
public class SpringUtil implements ApplicationContextAware {

	private static ApplicationContext applicationContext;
	
	private SpringUtil(){}
	
	public static Object getBean(String beanName) 
			throws BeansException {
		return applicationContext.getBean(beanName);
		
	}
	
	
	public static ApplicationContext getApplicationContext() {
		return applicationContext;
		
	}

	@Override
	public void setApplicationContext(ApplicationContext arg0) 
			throws BeansException {
		SpringUtil.applicationContext = arg0;

	}

}
