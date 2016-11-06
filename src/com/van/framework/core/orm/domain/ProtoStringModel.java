package com.van.framework.core.orm.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;


/** 
 * @className: ProtoStringModel.java<br/>
 * @classDescription: 实体父类 id:UUID<br/>
 * @createTime: 2015年4月26日
 * @author Van
 */

@MappedSuperclass
public abstract class ProtoStringModel extends ProtoModel<String> {
	private static final long serialVersionUID = 1L;
	
	protected ProtoStringModel(){}
	
	protected ProtoStringModel(ProtoStringModel src){
		super(src);
	}
	
	@Id
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	@GeneratedValue(generator = "idGenerator")
	protected String id;
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	@Override
	public String getIdentifier(){
		return this.getId();
	}
	
	@Override
	public void setIdentifier(String serializable){
		this.setId(serializable);
	}
	
	
}
