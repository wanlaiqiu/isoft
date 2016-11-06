package com.van.products.web.controller.Abstract;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.Validate;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.van.framework.core.orm.domain.Identifier;
import com.van.products.service.GeneralService;

/** 
 * @className: AbstractController.java<br/>
 * @classDescription: 所有的Controller父类，建议继承此类<br/>
 * @createTime: 2015年4月28日
 * @author Van
 */

public abstract class BaseController<KEY extends Serializable, T extends Identifier<KEY>> 
			extends AbstractController {
	
	protected Logger logger = Logger.getLogger(this.getClass());
	
	protected static String NULL = null;
	
	protected Class<T> entityClass;
	
	@Autowired
	protected GeneralService generalService;
	
	
	@SuppressWarnings("unchecked")
	public BaseController(){
		if(this.getClass().getGenericSuperclass() instanceof ParameterizedType){
			Type[] types = ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments();
			this.entityClass = (Class<T>) types[1];
		}
	}
	
	public BaseController(Class<T> entityClass){
		this.entityClass = entityClass;
	}
	
	@PostConstruct 
	public void init(){
		Validate.notNull(this.entityClass, "[entityClass] MUST be set");
	}
	
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse) throws Exception {
		return super.handleRequest(httpservletrequest, httpservletresponse);
	}
	
}
