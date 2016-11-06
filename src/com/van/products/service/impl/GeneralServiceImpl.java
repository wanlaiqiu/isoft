package com.van.products.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.van.framework.core.orm.domain.Identifier;
import com.van.framework.core.orm.domain.vo.Pagination;
import com.van.products.dao.GeneralDao;
import com.van.products.service.GeneralService;

/** 
 * @className: GeneralServiceImpl.java<br/>
 * @classDescription: Description here<br/>
 * @createTime: 2015年4月28日
 * @author Van
 */

@Service(value = "generalService")
public class GeneralServiceImpl implements GeneralService {
	private static final long serialVersionUID = 1L;

	private static final Logger logger = Logger.getLogger(GeneralServiceImpl.class);
	
	@Autowired
	private GeneralDao generalDao;

	@Override
	public <KEY extends Serializable, T extends Identifier<KEY>> 
			T getById(Class<T> clazz, KEY id) throws DataAccessException {
		
		return this.generalDao.getById(clazz, id);
	}

	@Override
	public <KEY extends Serializable, T extends Identifier<KEY>> 
			List<T> getByIds(Class<T> clazz, Collection<KEY> ids) throws DataAccessException {
		
		if(CollectionUtils.isEmpty(ids)){
			class SubDataAccessException extends DataAccessException{
				private static final long serialVersionUID = 1L;
				public SubDataAccessException(String msg) {
					super(msg);
				}
			}
			SubDataAccessException access = new SubDataAccessException("[ids]不能为空");
			logger.error("[ids]不能为空", access);
			throw access;
		}
		return this.generalDao.getByIds(clazz, ids);
	}

	@Override
	public <KEY extends Serializable, T extends Identifier<KEY>> List<T> 
			getListByDetachedCriteria(DetachedCriteria dc) {
		
		return this.generalDao.getListByDetachedCriteria(dc);
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public <KEY extends Serializable, T extends Identifier<KEY>> T save(T instance) {
		
		return this.generalDao.save(instance);
	}

	@Override
	@Transactional
	public <KEY extends Serializable, T extends Identifier<KEY>> T merge(T instance) {

		return this.generalDao.merge(instance);
	}

	@Override
	@Transactional
	public <KEY extends Serializable, T extends Identifier<KEY>> int delete(T instance) {

		return this.generalDao.delete(instance);
	}

	@Override
	@Transactional
	public <KEY extends Serializable, T extends Identifier<KEY>> int deleteByIds(Class<T> clazz, List<KEY> ids) {
		
		return this.generalDao.deleteByIds(clazz, ids);
	}

	@Override
	public <KEY extends Serializable, T extends Identifier<KEY>> 
			T getByProperty(Class<T> clazz, String propertyName, Object value) {

		Assert.hasText(propertyName, "[propertyName] MUST NOT be empty");
		Assert.hasText(String.valueOf(value), "[value] is required");
		return this.generalDao.getByProperty(clazz, propertyName, value);
	}

	@Override
	public <KEY extends Serializable, T extends Identifier<KEY>> 
			List<T> findForListByProperty(Class<T> clazz, String propertyName, Object value) {
		
		Assert.hasText(propertyName, "[propertyName] MUST NOT be empty");
		Assert.hasText(String.valueOf(value), "[value] is required");
		return this.generalDao.findForListByProperty(clazz, propertyName, value);
	}

	@Override
	public <KEY extends Serializable, T extends Identifier<KEY>> 
			List<T> findForList(Class<T> clazz) {

		return this.findForListByHQL(clazz, null, null);
	}

	@Override
	public <KEY extends Serializable, T extends Identifier<KEY>> 
			List<T> findForListByHQL(Class<T> clazz, String whereHQL, Object[] values) {
		
		return this.generalDao.findForListByHQL(clazz, whereHQL, values);
	}

	@Override
	public <KEY extends Serializable, T extends Identifier<KEY>> 
			List<T> findForPageList(Class<T> clazz, int pageNo, int pageSize) {

		return this.findForPageListByHQL(clazz, "", null, pageNo, pageSize);
	}
	
	@Override
	public <KEY extends Serializable, T extends Identifier<KEY>> 
			List<T> findForPageListByHQL(Class<T> clazz, String whereHQL, Object[] values, int start, int pageSize) {
		
		return this.generalDao.findForPageListByHQL(clazz, whereHQL, values, start, pageSize);
	}
	
	@Override
	public List<Map<String, Object>> findForPageListByHQL(String hql, Object[] values, int start, int pageSize) {

		Assert.hasText(hql, "[hql] MUST NOT be empty");
		return this.generalDao.findForPageListByHQL(hql, values, start, pageSize);
	}
	
	@Override
	public List<Map<String, String>> findForPageStringListByHQL(String hql, Object[] values, int start, int pageSize) {
		
		return this.findForPageListByHQL(hql, values, start, pageSize)
					.stream().map(map -> {
						Map<String, String> mapper = new HashMap<>();
						for(Entry<String, Object> entry : map.entrySet()){
							mapper.put(entry.getKey(), String.valueOf(entry.getValue()));
						}
						return mapper;
						
					}).collect(Collectors.toList());
	}
	
	@Override
	public List<Map<String, Object>> findForPageListBySQL(String sql, Object[] values, int start, int pageSize) {
		
		Assert.hasText(sql, "[sql] MUST NOT be empty");
		return this.generalDao.findForPageListBySQL(sql, values, start, pageSize);
	}
	
	@Override
	public List<Map<String, String>> findForPageStringListBySQL(String sql, Object[] values, int start, int pageSize) {
		
		return this.findForPageListBySQL(sql, values, start, pageSize)
					.stream().map(map -> {
						Map<String, String> mapper = new HashMap<>();
						for(Entry<String, Object> entry : map.entrySet()){
							mapper.put(entry.getKey(), String.valueOf(entry.getValue()));
						}
						return mapper;
						
					}).collect(Collectors.toList());
	}
	
	@Override
	public Pagination findForPaginationBySQL(String sql, Object[] values, int pageNo, int pageSize) {
		
		Assert.hasText(sql, "[sql] MUST NOT be empty");
		return this.generalDao.findForPaginationBySQL(sql, values, pageNo, pageSize);
	}
	
	@Override
	public Pagination findForPaginationByHQL(String hql, Object[] values, int pageNo, int pageSize) {
	
		Assert.hasText(hql, "[hql] MUST NOT be empty");
		return this.generalDao.findForPaginationByHQL(hql, values, pageNo, pageSize);
	}

	@Override
	public <KEY extends Serializable, T extends Identifier<KEY>> 
			Integer findForInteger(Class<T> clazz) {

		return this.findForIntegerByHQL(clazz,"", null);
	}
	
	@Override
	public <KEY extends Serializable, T extends Identifier<KEY>> 
			Integer findForIntegerByHQL(Class<T> clazz, String whereHQL, Object[] values) {
		
		String hql = "select count(e) from " + clazz.getSimpleName() + " e " + (StringUtils.isEmpty(whereHQL) ? "" : " " + whereHQL);
		return this.findForIntegerByHQL(hql, values);
	}
	
	@Override
	public Integer findForIntegerByHQL(String hql, Object[] values) {
		
		Assert.hasText(hql, "[hql] MUST NOT be empty");
		return this.generalDao.findForIntegerByHQL(hql, values);
	}
	
	@Override
	public Integer findForIntegerBySQL(String sql, Object[] values) {
		
		Assert.hasText(sql, "[sql] MUST NOT be empty");
		return this.generalDao.findForIntegerBySQL(sql, values);
	}

	@Override
	public <KEY extends Serializable, T extends Identifier<KEY>> 
			List<T> findForListByCriteria(DetachedCriteria dc) {

		return this.generalDao.findForListByCriteria(dc);
	}
	
	@Override
	@Transactional
	public int executeBySQL(String sql, Object[] values) {
		
		Assert.hasText(sql, "[sql] MUST NOT be empty");
		return this.generalDao.executeBySQL(sql, values);
	}
	
	@Override
	@Transactional
	public int executeByHQL(String hql, Object[] values) {
		
		Assert.hasText(hql ,"[hql] MUST NOT be empty");
		return this.generalDao.executeByHQL(hql, values);
	}
	
	@Override
	@Transactional
	public int executeBySQLGroup(List<String> sqlGroup, List<Object[]> params) {
		
		if(CollectionUtils.isEmpty(sqlGroup))
			throw new IllegalArgumentException("[sqlGroup] MUST NOT be empty");
		if(CollectionUtils.isNotEmpty(params) && sqlGroup.size() != params.size())
			throw new IllegalArgumentException("[sqlGroup] 与 [params] size()不一致！");
			
		return this.generalDao.executeBySQLGroup(sqlGroup, params);
	}
	
	@Override
	@Transactional
	public int executeByHQLGroup(List<String> hqlGroup, List<Object[]> params) {
		
		if(CollectionUtils.isEmpty(hqlGroup))
			throw new IllegalArgumentException("[hqlGroup] MUST NOT be empty");
		if(CollectionUtils.isNotEmpty(params) && hqlGroup.size() != params.size())
			throw new IllegalArgumentException("[hqlGroup] 与 [params] size()不一致！");
		
		return this.generalDao.executeByHQLGroup(hqlGroup, params);
	}

	@SuppressWarnings("deprecation")
	@Override
	public <KEY extends Serializable, T extends Identifier<KEY>> 
			List<T> loadAll(Class<T> clazz) {

		return this.generalDao.loadAll(clazz);
	}



	

}
