package com.van.framework.core.orm.domain;

import java.io.Serializable;

import javax.persistence.MappedSuperclass;

/** 
 * @className: ProtoModel.java<br/>
 * @classDescription: None Description<br/>
 * @createTime: 2015年4月26日
 * @author Van
 */

@MappedSuperclass
public abstract class ProtoModel<T extends Serializable> implements Identifier<T>,Serializable {
	private static final long serialVersionUID = 1L;
	
	protected ProtoModel() { }
	
	protected ProtoModel(ProtoModel<T> src){
		this.setIdentifier(src.getIdentifier());
	}
	
	@Override
	public boolean useCache() {
		return false;
	}
	
	@Override
	public boolean equals(Object o) {
		if(o == this){
			return true;
		}
		if(o == null || this.getClass() != o.getClass()){
			return false;
		}
		@SuppressWarnings("unchecked")
		ProtoModel<T> generalModel = (ProtoModel<T>) o;
		Serializable id = this.getIdentifier();
		
		return null != id && id.equals(generalModel.getIdentifier());
	}
	
	@Override
	public int hashCode() {
		if(null == this.getIdentifier()){
			return super.hashCode();
		}
		return this.getIdentifier().hashCode();
	}
	
	@Override
	public String toString() {
		return new StringBuilder()
				.append(this.getClass())
				.append(" --> id:")
				.append(this.getIdentifier())
				.toString();
	}

}
