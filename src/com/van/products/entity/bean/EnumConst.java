package com.van.products.entity.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.van.framework.core.orm.domain.ProtoStringModel;

/** 
 * @className: EnumConst.java<br/>
 * @classDescription: 常量数据，性别、状态、民族等<br/>
 * @createTime: 2015年4月26日
 * @author Van
 */

@Entity
@Table(name = "VAN_ENUMCONST")
public class EnumConst extends ProtoStringModel {
	private static final long serialVersionUID = 1L;

	@Column(name = "`NAME`")
	private String name;//名称
	
	@Column(name = "`CODE`")
	private String code;//编码
	
	@Column(name = "`NAMESPACE`")
	private String nameSpace;//命名空间
	
	@Column(name = "IS_DEFAULT")
	private String isDefault;//默认值
	
	@Temporal(TemporalType.DATE)
	@Column(name = "CREATE_DATE")
	private Date createDate;//创建时间
	
	@Column(name = "`NOTE`")
	private String note;//注释说明

	
	public EnumConst() {
		super();
	}

	public EnumConst(ProtoStringModel src) {
		super(src);
	}

	public EnumConst(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getNameSpace() {
		return nameSpace;
	}

	public void setNameSpace(String nameSpace) {
		this.nameSpace = nameSpace;
	}

	public String getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	
}
