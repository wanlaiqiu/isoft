package com.van.products.dao.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.van.framework.core.orm.domain.Identifier;
import com.van.framework.core.orm.domain.vo.Pagination;
import com.van.products.dao.GeneralDao;

/** 
 * @className: GeneralHibernateDao.java<br/>
 * @classDescription: Description here<br/>
 * @createTime: 2015年4月28日
 * @author Van
 */

@Repository(value = "generalDao")
@SuppressWarnings("unchecked")
public class GeneralHibernateDao implements GeneralDao {
	private static final long serialVersionUID = 1L;

	protected static final Logger logger = Logger.getLogger(GeneralHibernateDao.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public <KEY extends Serializable, T extends Identifier<KEY>> 
			T getById(Class<T> clazz, KEY id) {
		
		try {
			T instance = (T) this.getSession().load(clazz, id);
			return instance;
		} catch (RuntimeException e) {
			logger.error(e);
			throw e;
		}
	}

	@Override
	public <KEY extends Serializable, T extends Identifier<KEY>> 
			List<T> getByIds(Class<T> clazz, Collection<KEY> ids) {
		
		String hql = "from " + clazz.getSimpleName() + " e where e.id in (:ids)";
		try {
			Query query = this.getSession().createQuery(hql);
			query.setParameterList("ids", ids);
			return query.list();
			
		} catch (RuntimeException e) {
			logger.error("获取失败！", e);
		}
		return Collections.emptyList();
	}
	
	@Override
	public <KEY extends Serializable, T extends Identifier<KEY>> 
		List<T> getListByDetachedCriteria(final DetachedCriteria dc) {
		
		Criteria query = dc.getExecutableCriteria(this.getSession());
		return query.list();
	}

	@Override
	public <KEY extends Serializable, T extends Identifier<KEY>> T save(T instance) {
		
		return this.merge(instance);
	}

	@Override
	public <KEY extends Serializable, T extends Identifier<KEY>> T merge(T instance) {
		
		return (T) this.getSession().merge(instance);
	}

	@Override
	public <KEY extends Serializable, T extends Identifier<KEY>> int delete(T instance) {
		
		int key = 0;
		try {
			this.getSession().delete(instance);
			key = 1;
			
			return key;
		} catch (RuntimeException e) {
			key = 0;
			logger.error("删除失败！", e);
			throw e;
		}
		
	}

	@Override
	public <KEY extends Serializable, T extends Identifier<KEY>> 
		int deleteByIds(Class<T> clazz, List<KEY> ids) {
		
		int key = 0;
		String hql = "delete from " + clazz.getSimpleName() + " e where e.id in (:ids)";
		try {
			Query query = this.getSession().createQuery(hql);
			query.setParameterList("ids", ids);
			key = query.executeUpdate();
			
		} catch (RuntimeException e) {
			logger.error("删除失败！", e);
			key = 0;
			throw e;
		}
		return key;
	}

	@Override
	public <KEY extends Serializable, T extends Identifier<KEY>> 
		T getByProperty(Class<T> clazz, String propertyName, Object value) {
		
		String hql = "from " + clazz.getSimpleName() + " e where e." + propertyName + "=:value";
		try {
			Query query = this.getSession().createQuery(hql);
			query.setParameter("value", value);
			T instabce = (T) query.uniqueResult();
			return instabce;
		} catch (RuntimeException e) {
			logger.error("根据属性获取失败！", e);
			throw e;
		}
	}

	@Override
	public <KEY extends Serializable, T extends Identifier<KEY>> 
		List<T> findForListByProperty(Class<T> clazz, String propertyName, Object value) {
		
		String hql = "from " + clazz.getSimpleName() + " e where e." + propertyName + "=:value";
		Query query = this.getSession().createQuery(hql);
		query.setParameter("value", value);

		return query.list();
	}

	@Override
	public <KEY extends Serializable, T extends Identifier<KEY>> 
		List<T> findForListByCriteria(DetachedCriteria dc) {
		
		Criteria criteria = dc.getExecutableCriteria(this.getSession());
		
		return criteria.list();
	}

	@Override
	public <KEY extends Serializable, T extends Identifier<KEY>> List<T> loadAll(Class<T> clazz) {
		String hql = "from " + clazz.getSimpleName() + " e ";
		return this.createHQLQuery(hql, null).list();
	}
	
	public <KEY extends Serializable, T extends Identifier<KEY>> 
			List<T> findForListByHQL(Class<T> clazz, String whereHQL, Object[] values){
		
		String hql = "from " + clazz.getSimpleName() + " e " + (StringUtils.isEmpty(whereHQL) ? "" : whereHQL);
		Query query = this.createHQLQuery(hql, values);
		return query.list();
	}

	@Override
	public <KEY extends Serializable, T extends Identifier<KEY>>
			List<T> findForPageListByHQL(Class<T> clazz, String whereHQL, Object[] values, int start, int pageSize) {
		
		String hql = "from " + clazz.getSimpleName() + " e " + (StringUtils.isEmpty(whereHQL) ? "" : whereHQL);
		Query query = this.createHQLQuery(hql, values);
		return query.setFirstResult(start).setMaxResults(pageSize).list();
	}
	
	@Override
	public List<Map<String, Object>> findForPageListByHQL(String hql, Object[] values, int start, int pageSize) {
		
		Query query = this.createHQLQuery(hql, values);
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		
		return query.setFirstResult(start).setMaxResults(pageSize).list();
	}
	
	@Override
	public List<Map<String, Object>> findForPageListBySQL(String sql, Object[] values, int start, int pageSize) {
		
		Query query = this.createSQLQuery(sql, values);
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP)
			 .setFirstResult(start).setMaxResults(pageSize);
		
		return query.list();
	}
	
	@Override
	public Pagination findForPaginationBySQL(String sql, Object[] values, int pageNo, int pageSize) {
		
		int totalCount = this.findForIntegerBySQL(this.getRowCountSQL(sql), values).intValue();
		if(totalCount > 0){
			Pagination page = new Pagination(pageNo, pageSize, totalCount);
			List<?> list = this.findForPageListBySQL(sql, values, page.getFirstResult(), pageSize);
			page.setList(list);
			return page;
			
		}
		return null;
	}
	
	@Override
	public Pagination findForPaginationByHQL(String hql, Object[] values, int pageNo, int pageSize) {

		int totalCount = this.findForIntegerByHQL(this.getRowCountHQL(hql), values).intValue();
		if(totalCount > 0){
			Pagination page = new Pagination(pageNo, pageSize, totalCount);
			List<?> list = this.findForPageListByHQL(hql, values, page.getFirstResult(), pageSize);
			page.setList(list);
			return page;
		}
		return null;
	}

	@Override
	public Integer findForIntegerByHQL(String hql, Object[] values) {
		
		Query query = this.createHQLQuery(hql, values);
		Long count = (Long) query.uniqueResult();
		if(null == count)
			return -1;
		
		return count.intValue();
	}
	
	@Override
	public Integer findForIntegerBySQL(String sql, Object[] values) {
		
		Query query = this.createSQLQuery(sql, values);
		Object value = query.uniqueResult();
		if(value == null)
			return -1;
		if(value.getClass().equals(BigDecimal.class))
			return ((BigDecimal) value).intValue();
		if(value.getClass().equals(BigInteger.class))
			return ((BigInteger) value).intValue();
					
		return (Integer) value;
	}
	
	@Override
	public int executeByHQL(String hql, Object[] values) {
		
		SQLHandle(hql);
		Integer key = 0;
		try {
			Query query = this.createHQLQuery(hql, values);
			key = query.executeUpdate();
			
			return key;
		} catch (RuntimeException e) {
			logger.error("execute ERROR with [" + hql + "]", e);
			throw e;
		}
	}
	
	@Override
	public int executeBySQL(String sql, Object[] values) {
		
		SQLHandle(sql);
		Integer key = 0;
		try {
			Query query = this.createSQLQuery(sql, values);
			key = query.executeUpdate();
			
			return key;
		} catch (RuntimeException e) {
			logger.error("execute ERROR with [" + sql + "]", e);
			throw e;
		}
	}
	
	@Override
	public int executeBySQLGroup(List<String> sqlGroup, List<Object[]> params) {
		
		int key = 0;
		try {
			for(int i = 0, len = sqlGroup.size(); i < len; i++){
				SQLHandle(sqlGroup.get(i));
				Query query = this.createSQLQuery(sqlGroup.get(i), params.get(i));
				query.executeUpdate();
			}
			key = 1;
			return key;
		} catch (RuntimeException e) {
			logger.error("executeBySQLGroup ERROR", e);
			throw e;
		}
	}
	
	@Override
	public int executeByHQLGroup(List<String> hqlGroup, List<Object[]> params) {

		int key = 0;
		try {
			for(int i = 0, len = hqlGroup.size(); i < len; i++){
				String hql = hqlGroup.get(i);
				SQLHandle(hql);
				Query query = this.createHQLQuery(hql, CollectionUtils.isEmpty(params) ? null : params.get(i));
				query.executeUpdate();
			}
			key = 1;
			return key;
		} catch (RuntimeException e) {
			logger.error("executeByHQLGroup ERROR", e);
			throw e;
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	protected static void SQLHandle(String shql){
		
		String tmpHSQL = shql;
		//不检测insert语句
		if(Pattern.compile("^\\s*insert", Pattern.CASE_INSENSITIVE).matcher(shql).find())
			return;

		//去掉所有的子句
		String regex = "(\\()[^\\(\\)]*?(\\))";
		Pattern pattern = Pattern.compile(regex,Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(shql);
		while(matcher.find()){
			shql = shql.replaceAll(regex, "");
		}
		//剩余语句中检测where关键字
		if(!Pattern.compile("\\swhere\\s", Pattern.CASE_INSENSITIVE).matcher(shql).find()){
			throw new RuntimeException(new StringBuilder("[").append(tmpHSQL).append("] ").append("缺少where从句！！").toString());
		}
		
	}
	
	protected String getRowCountSQL(String sql){
		
		int from = sql.toLowerCase().indexOf("from");
		String fromSQL = sql.substring(from);
		
		return "select count(*) " + fromSQL;
	}
	
	protected String getRowCountHQL(String hql){
		
		int from = hql.toLowerCase().indexOf("from");
		String projectionHQL = hql.substring(0, from);
		hql = hql.substring(from);
		String rowCountHQL = hql.replaceAll("\\sfetch\\s", " ");
		int order = rowCountHQL.indexOf("order");
		if(order > 0)
			rowCountHQL = rowCountHQL.substring(0, order);
		
		return this.wrapProjection(projectionHQL) + rowCountHQL;
	}
	
	protected String wrapProjection(String projection){
		
		if(projection.indexOf("select") == -1)
			return "select count(*) ";
		String splitStr = projection;
		if(projection.indexOf(",") != -1){
			splitStr = projection.substring(0, projection.indexOf(","));
		}
		//考虑剩余的字符串三种情况
		//1. select e.id 无需处理
		//2. select e.id eid
		//3. select e.id as eid
		
		//去掉select关键字前面可能存在的空格
		splitStr = splitStr.replaceAll("^\\s+", "");
		//过滤多个空格换为单个空格
		splitStr = splitStr.replaceAll("\\s+", " ");
		
		String[] array = splitStr.split(" ");
		splitStr = array[0] + array[1];
		
		return splitStr.replace("select", "select count(") + ")";
		
	}
	
	protected  Query createHQLQuery(String hql, Object[] values){
		
		Assert.hasText(hql, "[hql] MUST NOT be null");
		Query query = this.getSession().createQuery(hql);
		if(null != values && values.length > 0){
			for(int i = 0, len = values.length; i < len; i++){
				query.setParameter(i, values[i]);
			}
		}
		return query;
	}
	
	protected Query createSQLQuery(String sql, Object[] values){
		
		Assert.hasText(sql, "[sql] MUST NOT be null");
		Query query = this.getSession().createSQLQuery(sql);
		if(null != values && values.length > 0){
			for(int i = 0, len = values.length; i < len; i++){
				query.setParameter(i, values[i]);
			}
		}
		return query;
	}
	
	protected Session getSession(){
		
		return this.sessionFactory.getCurrentSession();
	}

}
