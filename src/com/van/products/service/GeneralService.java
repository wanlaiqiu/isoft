package com.van.products.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.dao.DataAccessException;

import com.van.framework.core.orm.domain.Identifier;
import com.van.framework.core.orm.domain.vo.Pagination;

/** 
 * @className: GeneralService.java<br/>
 * @classDescription: 泛型加在对应方法上，减少依赖<br/>
 * @createTime: 2015年4月28日
 * @author Van
 */

public interface GeneralService extends Serializable {
	
	/**
	 * <code>根据ID获取对象<br/></code>
	 * @param clazz 获取的对象
	 * @param id 对象ID
	 * @return 
	 * @throws DataAccessException
	 */
	public <KEY extends Serializable,T extends Identifier<KEY>> 
			T getById(Class<T> clazz, KEY id) throws DataAccessException;
	
	/**
	 * <code>根据ID集合获取对象集合<br/></code>
	 * @param clazz
	 * @param ids
	 * @return
	 * @throws DataAccessException
	 */
	public <KEY extends Serializable,T extends Identifier<KEY>> 
			List<T> getByIds(Class<T> clazz, Collection<KEY> ids) throws DataAccessException;
	
	public <KEY extends Serializable,T extends Identifier<KEY>>
			List<T> getListByDetachedCriteria(DetachedCriteria dc);
	
	/**
	 * <code>存储对象<br/></code>
	 * @param instance 待保存的对象
	 * @return
	 */
	public <KEY extends Serializable,T extends Identifier<KEY>> 
			T save(T instance);
	
	/**
	 * <code>合并对象<br/></code>
	 * @param instance
	 * @return
	 */
	public <KEY extends Serializable,T extends Identifier<KEY>> 
			T merge(T instance);
	
	/**
	 * <code>删除对象<br/></code>
	 * @param instance 待删除的对象
	 * @return
	 */
	public <KEY extends Serializable,T extends Identifier<KEY>> 
			int delete(T instance);
	
	/**
	 * <code>根据ID集合删除多个对象<br/></code>
	 * @param clazz 要操作的对象
	 * @param ids ID集合
	 * @return
	 */
	public <KEY extends Serializable,T extends Identifier<KEY>> 
			int deleteByIds(Class<T> clazz, List<KEY> ids);
	
	/**
	 * <code>根据对象属性获取该对象（一般用于获取唯一的结果）<br/></code>
	 * @param clazz 待获取的对象
	 * @param propertyName 对象的属性名称
	 * @param value 属性的值
	 * @return
	 */
	public <KEY extends Serializable,T extends Identifier<KEY>> 
			T getByProperty(Class<T> clazz, String propertyName, Object value);
	
	/**
	 * <code>根据对象的属性获取对象（一般用于获取多个结果）<br/></code>
	 * @param clazz 待获取的对象
	 * @param propertyName 属性名称
	 * @param value 属性值
	 * @return
	 */
	public <KEY extends Serializable,T extends Identifier<KEY>> 
			List<T> findForListByProperty(Class<T> clazz, String propertyName, Object value);
	
	/**
	 * <code>查找对应的对象所有结果集<br/></code>
	 * @param clazz 待获取的对象class
	 * @return
	 */
	public <KEY extends Serializable,T extends Identifier<KEY>> 
			List<T> findForList(Class<T> clazz);
	
	/**
	 * <code>查找符合where条件的所有对象集合<br/>
	 * 	eg: (Entity.class,"where e.name=? and e.many.id=?",new Object[]{"123","456"})<br/>
	 * </code>
	 * @param clazz 待获取的对象class
	 * @param whereHQL 条件HQL
	 * @param values 条件值
	 * @return
	 */
	public <KEY extends Serializable,T extends Identifier<KEY>> 
			List<T> findForListByHQL(Class<T> clazz, String whereHQL, Object[] values);
		
	/**
	 * <code>分页获取对象<br/></code>
	 * @param clazz 对象class
	 * @param start 起始行
	 * @param pageSize 获取结果条数
	 * @return
	 */
	public <KEY extends Serializable,T extends Identifier<KEY>> 
			List<T> findForPageList(Class<T> clazz, int start, int pageSize);

	/**
	 * <code>根据条件，分页获取对象<br/></code>
	 * @param clazz 对象class
	 * @param whereHQL 限制条件HQL
	 * @param values 条件值
	 * @param start 起始行
	 * @param pageSize 结果条数
	 * @return
	 */
	public <KEY extends Serializable,T extends Identifier<KEY>>
	 		List<T> findForPageListByHQL(Class<T> clazz, String whereHQL, Object[] values, int start, int pageSize);
	
	/**
	 * <code>根据HQL获取结果集，结果集为Map&lt;String, Object&gt;泛型的List集合<br/></code>
	 * @param hql HQL
	 * @param values 条件值
	 * @param start 起始行
	 * @param pageSize 结果条数
	 * @return List&lt;Map&lt;String, Object&gt;
	 */
	public List<Map<String, Object>> findForPageListByHQL(String hql, Object[] values, int start, int pageSize);
	
	/**
	 * <code>根据HQL获取结果集，结果集为Map&lt;String, String&gt;泛型的List集合<br/></code>
	 * @param hql HQL
	 * @param values 条件值
	 * @param start 起始行
	 * @param pageSize 结果条数
	 * @return List&lt;Map&lt;String, Object&gt;
	 */
	public List<Map<String, String>> findForPageStringListByHQL(String hql, Object[] values, int start, int pageSize);
	
	/**
	 * <code>根据SQL获取结果集，结果集为Map泛型的List集合<br/></code>
	 * @param sql SQL
	 * @param values 条件值
	 * @param start 起始行
	 * @param pageSize 结果条数
	 * @return &lt;List&lt;Map&lt;String, Object&gt;&gt;
	 */
	public List<Map<String, Object>> findForPageListBySQL(String sql, Object[] values, int start, int pageSize);
	
	/**
	 * <code>根据SQL获取结果集，结果集为Map&lt;String, String&gt;泛型的List集合<br/></code>
	 * @param sql SQL
	 * @param values 条件值
	 * @param start 起始行
	 * @param pageSize 结果条数
	 * @return List&lt;Map&lt;String, String&gt;
	 */
	public List<Map<String, String>> findForPageStringListBySQL(String sql, Object[] values, int start, int pageSize);

	/**
	 * <code>根据SQL获取分页对象，内置分页相关参数，参考{@linkplain com.van.framework.core.orm.domain.vo.Pagination Pagination}<br/></code>
	 * @param sql 查询SQL
	 * @param values 条件值
	 * @param pageNo 页码（注意：不是起始行数）
	 * @param pageSize 分页大小
	 * @return
	 */
	public Pagination findForPaginationBySQL(String sql, Object[] values, int pageNo, int pageSize);
	
	/**
	 * <code>根据HQL获取分页对象，内置分页相关参数，参考{@linkplain com.van.framework.core.orm.domain.vo.Pagination Pagination}<br/></code>
	 * @param hql 查询HQL
	 * @param values 条件值
	 * @param pageNo 页码（注意：不是起始行数）
	 * @param pageSize 分页大小
	 * @return
	 */
	public Pagination findForPaginationByHQL(String hql, Object[] values, int pageNo, int pageSize);
	
	/**
	 * <code>获取对象总数<br/></code>
	 * @param clazz 对象class
	 * @return count总数
	 */
	public <KEY extends Serializable,T extends Identifier<KEY>> 
			Integer findForInteger(Class<T> clazz);
	
	/**
	 * <code>获取符合条件的对象总数<br/></code>
	 * @param clazz 对象class
	 * @param whereHQL HQL条件
	 * @param values 条件值
	 * @return count总数
	 */
	public <KEY extends Serializable, T extends Identifier<KEY>> 
			Integer findForIntegerByHQL(Class<T> clazz, String whereHQL, Object[] values);
	
	/**
	 * <code>根据HQL统计总数<br/>
	 * eg: select count(e) from EnityClass e where e.name=? and e.many.id=?<br/>
	 * </code>
	 * @param hql HQL
	 * @param values 条件值
	 * @return
	 */
	public Integer findForIntegerByHQL(String hql, Object[] values);
	
	/**
	 * <code>根据SQL统计总数<br/></code>
	 * @param sql SQL
	 * @param values 条件值
	 * @return
	 */
	public Integer findForIntegerBySQL(String sql, Object[] values);
	
	/**
	 * <code>离线查询<br/></code>
	 * @param dc {@link DetachedCriteria}
	 * @return
	 */
	public <KEY extends Serializable,T extends Identifier<KEY>> 
			List<T> findForListByCriteria(DetachedCriteria dc);
	
	/**
	 * <code>执行SQL[update|delete|insert]语句<br/></code>
	 * @param sql SQL
	 * @param values 条件值
	 * @return int
	 */
	public int executeBySQL(String sql, Object[] values);
	
	/**
	 * <code>执行HQL[update|delete|insert]<br/></code>
	 * @param hql HQL
	 * @param values 条件值
	 * @return int
	 */
	public int executeByHQL(String hql, Object[] values);
	
	/**
	 * <code>批量执行SQL[update|delete|insert]<br/></code>
	 * @param sqlGroup SQL 集合
	 * @param params 参数集合，每一条SQL对应一个Object[]数组参数，不需要参数时传递null
	 * @return
	 */
	public int executeBySQLGroup(List<String> sqlGroup, List<Object[]> params);
	
	/**
	 * <code>批量执行HQL[update|delete|insert]<br/></code>
	 * @param hqlGroup HQL 集合
	 * @param params 参数集合，每一条HQL对应一个Object[]数组参数，不需要参数时传递null
	 * @return
	 */
	public int executeByHQLGroup(List<String> hqlGroup, List<Object[]> params);
	
	/**
	 * loadAll方法已过时
	 * @param clazz 对象class
	 * @return
	 */
	@Deprecated
	public <KEY extends Serializable,T extends Identifier<KEY>> 
			List<T> loadAll(Class<T> clazz);
	
	
}
