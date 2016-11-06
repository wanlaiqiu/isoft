package com.van.framework.core.orm.domain;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/** 
 * @className: AssignStringModel.java<br/>
 * @classDescription: Description here<br/>
 * @createTime: 2015年4月26日
 * @author Van
 */

@MappedSuperclass
public abstract class AssignStringModel extends ProtoModel<String> {
	private static final long serialVersionUID = 1L;

	@Id
	protected String id = null;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	@Override
	public String getIdentifier() {
		return this.getId();
	}
	
	@Override
	public void setIdentifier(String serializable) {
		this.id = serializable;
	}
	
}
