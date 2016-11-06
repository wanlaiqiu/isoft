package com.van.framework.core.orm.domain;

import java.io.Serializable;

/** 
 * @className: Identifier.java<br/>
 * @classDescription: None Description Current<br/>
 * @createTime: 2015年4月26日
 * @author Van
 */
public interface Identifier<T extends Serializable> extends Serializable {

	public abstract T getIdentifier();
	
	public abstract void setIdentifier(T serializable);
	
	public abstract boolean useCache();
}
