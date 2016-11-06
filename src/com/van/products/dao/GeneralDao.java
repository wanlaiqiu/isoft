package com.van.products.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;

import com.van.framework.core.orm.domain.Identifier;
import com.van.framework.core.orm.domain.vo.Pagination;

/** 
 * @className: EntityDAO.java<br/>
 * @classDescription: Description here<br/>
 * @createTime: 2015年4月27日
 * @author Van
 */

public interface GeneralDao extends Serializable {

	public <KEY extends Serializable, T extends Identifier<KEY>> T getById(Class<T> clazz, KEY id);
	
	public <KEY extends Serializable, T extends Identifier<KEY>> List<T> getByIds(Class<T> clazz, Collection<KEY> ids);
	
	public <KEY extends Serializable,T extends Identifier<KEY>> List<T> getListByDetachedCriteria(final DetachedCriteria dc);
	
	public <KEY extends Serializable, T extends Identifier<KEY>> T save(T instance);
	
	public <KEY extends Serializable, T extends Identifier<KEY>> T merge(T instance);
	
	public <KEY extends Serializable, T extends Identifier<KEY>> int delete(T instance);
	
	public <KEY extends Serializable, T extends Identifier<KEY>> int deleteByIds(Class<T> clazz, List<KEY> ids);
	
	public <KEY extends Serializable, T extends Identifier<KEY>> T getByProperty(Class<T> clazz, String propertyName, Object value);
	
	public <KEY extends Serializable, T extends Identifier<KEY>> List<T> findForListByProperty(Class<T> clazz, String propertyName, Object value);
	
	public <KEY extends Serializable, T extends Identifier<KEY>> List<T> findForListByCriteria(DetachedCriteria dc);
	
	@Deprecated
	public <KEY extends Serializable, T extends Identifier<KEY>> List<T> loadAll(Class<T> clazz);
	
	public <KEY extends Serializable, T extends Identifier<KEY>> List<T> findForListByHQL(Class<T> clazz, String whereHQL, Object[] values);
	
	public <KEY extends Serializable, T extends Identifier<KEY>> List<T> findForPageListByHQL(Class<T> clazz, String whereHQL, Object[] values, int start, int pageSize);

	public List<Map<String, Object>> findForPageListByHQL(String hql, Object[] values, int start, int pageSize);
	
	public List<Map<String, Object>> findForPageListBySQL(String sql, Object[] values, int start, int pageSize);
	
	public Pagination findForPaginationBySQL(String sql, Object[] values, int pageNo, int pageSize);

	public Pagination findForPaginationByHQL(String hql, Object[] values, int pageNo, int pageSize);
	
	public Integer findForIntegerByHQL(String hql, Object[] values);
	
	public Integer findForIntegerBySQL(String sql, Object[] values);
	
	public int executeBySQL(String sql, Object[] values);
	
	public int executeByHQL(String hql, Object[] values);
	
	public int executeBySQLGroup(List<String> sqlGroup, List<Object[]> params);
	
	public int executeByHQLGroup(List<String> hqlGroup, List<Object[]> params);
	
	
	
}
