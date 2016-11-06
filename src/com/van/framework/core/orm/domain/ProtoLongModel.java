package com.van.framework.core.orm.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;


/** 
 * @className: ProtoLongModel.java<br/>
 * @classDescription: 实体父类 id:AUTO<br/>
 * @createTime: 2015年4月26日
 * @author Van
 */

@MappedSuperclass
public abstract class ProtoLongModel extends ProtoModel<Long> {
	private static final long serialVersionUID = 1L;

	protected ProtoLongModel(){}
	
	protected ProtoLongModel(ProtoLongModel src){
		super(src);
	}
	
	@Id
	@GenericGenerator(name = "idGenerator", strategy = "increment") 
	@GeneratedValue(generator = "idGenerator")
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
