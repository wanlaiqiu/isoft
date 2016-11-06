package com.van.framework.core.orm.domain;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/** 
 * @className: ProtoSequenceModel.java<br/>
 * @classDescription: 实体父类 id:SEQUENCE<br/>
 * @createTime: 2015年4月26日
 * @author Van
 */

@MappedSuperclass
public abstract class ProtoSequenceModel extends ProtoModel<Long> {
	private static final long serialVersionUID = 1L;

	protected ProtoSequenceModel(){ }
	
	protected ProtoSequenceModel(ProtoSequenceModel src){
		super(src);
	}
	
	@Id
	protected Long id = Long.valueOf(0L);

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@Override
	public Long getIdentifier() {
		return this.getId();
	}
	
	@Override
	public void setIdentifier(Long serializable) {
		this.id = serializable;
		
	}
	
}
