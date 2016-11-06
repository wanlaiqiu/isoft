package com.van.framework.core.orm.domain;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.CollectionUtils;

/** 
 * @className: IDHelper.java<br/>
 * @classDescription: IDHelper<br/>
 * @createTime: 2015年4月26日
 * @author Van
 */
public abstract class IDHelper {

	public static final Comparator<Long> ID_ASC_COMPARATOR = (o1, o2) -> o1.compareTo(o2);
	
	public static final Comparator<Long> ID_DESC_COMPARATOR = (o1, o2) -> o2.compareTo(o1);
	
	@SuppressWarnings("unchecked")
	public static <E> List<E> getFields(Collection<E> beans, String fieldName){
		if(CollectionUtils.isEmpty(beans)){
			return Collections.emptyList();
		}
		List<E> rst = new ArrayList<E>(beans.size());
		for(Iterator<E> localIterator = beans.iterator();localIterator.hasNext();){
			Object bean = localIterator.next();
			if(null == bean)
				continue;
			try {
				rst.add((E) PropertyUtils.getProperty(bean, fieldName));
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			}
		}
		return rst;
	}
	
	public static <MODEL extends Identifier<? extends Serializable>> Map<Serializable, MODEL> toMap(Collection<MODEL> targets){
		Map<Serializable, MODEL> rst = new LinkedHashMap<Serializable, MODEL>();
		for(MODEL target : targets){
			rst.put(target.getIdentifier(), target);
		}
		return rst;
	}
	
	public static <T extends Serializable> List<Long> getLongIds(List<T> ids){
		if(CollectionUtils.isEmpty(ids))
			return Collections.emptyList();
		List<Long> localIds = new ArrayList<Long>();
		for(Serializable id : ids){
			localIds.add(Long.valueOf(Long.parseLong(id.toString())));
		}
		return localIds;
	}
	
	public static Long getLongId(Serializable id){
		if(id instanceof Number){
			return ((Number) id).longValue();
		}
		return Long.parseLong(id.toString());
	}
	
	public static int getIntId(Serializable id){
		if(id instanceof Number){
			return ((Number) id).intValue();
		}
		return Integer.parseInt(id.toString());
	}
	
	public static <T extends Identifier<?>> List<Serializable> getIdentifiers(Collection<T> entities){
		if(CollectionUtils.isEmpty(entities)){
			return Collections.emptyList();
		}
		List<Serializable> rst = new ArrayList<Serializable>();
		for(Identifier<?> entity : entities){
			rst.add(entity.getIdentifier());
		}
		return rst;
	}
	
	public static <T extends ProtoLongModel> List<Long> getLongIds(Collection<T> entities){
		if(CollectionUtils.isEmpty(entities)){
			return Collections.emptyList();
		}
		List<Long> rst = new ArrayList<Long>();
		for(ProtoLongModel entity : entities){
			rst.add(entity.getId());
		}
		return rst;
	}
	
	public static <T extends ProtoStringModel> List<String> getStringIds(Collection<T> entities){
		if(CollectionUtils.isEmpty(entities)){
			return Collections.emptyList();
		}
		List<String> rst = new ArrayList<String>();
		for(ProtoStringModel entity : entities){
			rst.add(entity.getId());
		}
		return rst;
	}
	
	public static <T extends Serializable> List<String> serializableList2StringList(List<T> list){
		List<String> rst = new ArrayList<String>();
		for(Serializable sze : list){
			rst.add(String.valueOf(sze));
		}
		return rst;
	}
	
	public static <T extends Identifier<? extends Serializable>> List<String> getStringIdentifiers(Collection<T> list){
		List<String> rst = new ArrayList<String>();
		for(Identifier<? extends Serializable> ident : list){
			if(null == ident)
				rst.add(null);
			rst.add(String.valueOf(ident.getIdentifier()));
		}
		return rst;
	}
	
	public static <T extends Identifier<? extends Serializable>> List<String> getStringList(Collection<? extends Serializable> ids){
		List<String> rst = new ArrayList<String>();
		for(Serializable id : ids){
			rst.add(String.valueOf(id));
		}
		return rst;
	}
	
	public static <T extends Identifier<? extends Serializable>> T findInList(List<T> list, Serializable id){
		if(null == id)
			return null;
		for(T ident : list){
			if(null != ident && id.equals(ident.getIdentifier())){
				return ident;
			}
		}
		return null;
	}
	
	public static <T extends Identifier<? extends Serializable>> T removeInList(List<T> list, Serializable id){
		if(null == id)
			return null;
		for(int i = 0, len = list.size(); i < len; i++){
			T ident = list.get(i);
			if(id.equals(ident.getIdentifier())){
				list.remove(i);
				return ident;
			}
		}
		return null;
	}
	
	public static <T extends Identifier<? extends Serializable>> T updateInList(List<T> list, T entity){
		if(null == entity || null == entity.getIdentifier())
			return null;
		for(int i = 0, len = list.size(); i < len; i++){
			T ident = list.get(i);
			if(entity.getIdentifier().equals(ident.getIdentifier())){
				list.set(i, entity);
				return ident;
			}
		}
		return null;
	}
	
	public static <T extends Object> String collection2String(Collection<T> list, String seperator){
		if(CollectionUtils.isEmpty(list))
			return "";
		StringBuilder rst = new StringBuilder();
		for(Iterator<T> local = list.iterator(); local.hasNext();){
			T o = local.next();
			if(null != o)
				rst.append(o).append(seperator);
		}
		rst.delete(rst.length() - seperator.length(), rst.length());
		return rst.toString();
	}
	
	
}
