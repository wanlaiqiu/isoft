package com.van.products.webservice.vo;

import java.io.Serializable;

/** 
 * @className: Property.java<br/>
 * @classDescription: 简单内容获取（全部以字符串形式传递）<br/>
 * @createTime: 2015年5月17日
 * @author Van
 */
public class Property implements Serializable, Comparable<Property> {
	private static final long serialVersionUID = -7599743175459281411L;
	
	private String propertyTitle;//属性标题(用于显示在页面上)
	private String propertyName;//属性名称
	private String propertyValue;//属性值
	private Integer sort = 0;//排序(用于显示时列的先后顺序)
	private Boolean like;//条件查询时：true:ike，false:eq
	private String operation;//操作
	private String dataType;//数据类型
	
	
	@Override
	public int compareTo(Property o) {
		if(null != o){
			if(o.sort > this.sort){
				return -1;
			} else if(o.sort < this.sort){
				return 1;
			}
			return 0;
		}
		
		return Integer.MAX_VALUE;
	}
	
	public String getPropertyTitle() {
		return propertyTitle;
	}
	public void setPropertyTitle(String propertyTitle) {
		this.propertyTitle = propertyTitle;
	}
	public String getPropertyName() {
		return propertyName;
	}
	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}
	public String getPropertyValue() {
		return propertyValue;
	}
	public void setPropertyValue(String propertyValue) {
		this.propertyValue = propertyValue;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	public Boolean getLike() {
		return like;
	}
	public void setLike(Boolean like) {
		this.like = like;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	
	
}
