package com.van.products.webservice.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.apache.commons.collections.CollectionUtils;

/** 
 * @className: OtherPlatformData.java<br/>
 * @classDescription: WebService传递数据 <br/>
 * @createTime: 2015年5月4日
 * @author Van
 */


public class OtherPlatformData implements Serializable, Comparable<OtherPlatformData> {
	private static final long serialVersionUID = 5639942232538389942L;
	
	private List<Property> properties;
	private Integer sort = 0;
	private String id;
	
	

	public List<Property> getProperties() {
		if(null != properties && properties.size() > 0)
			Collections.sort(properties);
		
		return properties;
	}

	public void setProperties(List<Property> properties) {
		this.properties = properties;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


	public static Map<String, String> propertyList2Map(List<Property> lists){
		if(null == lists || lists.size() == 0)
			return null;
		Map<String, String> map = new LinkedHashMap<String, String>();
		for(Property prop : lists){
			map.put(prop.getPropertyName(), prop.getPropertyValue());
		}
		
		return map;
	}
	
	public static List<Property> map2PropertyList(Map<String, String> map){
		if(null == map || map.size() == 0)
			return null;
		List<Property> lists = new ArrayList<Property>();
		for(Entry<String, String> entry : map.entrySet()){
			Property prop = new Property();
			prop.setPropertyName(entry.getKey());
			prop.setPropertyValue(entry.getValue());
			lists.add(prop);
		}
		
		return lists;
	}

	@Override
	public int compareTo(OtherPlatformData o) {
		if(null != o){
			if(o.sort > this.sort){
				return -1;
			}else if(o.sort < this.sort){
				return 1;
			}
		}
		return Integer.MAX_VALUE;
	}
}
