package com.van.framework.core.orm.domain.vo;
/** 
 * @className: Paginable.java<br/>
 * @classDescription: none Description <br/>
 * @createTime: 2015年4月29日
 * @author Van
 */
public interface Paginable {

	public int getTotalCount();
	
	public int getTotalPage();
	
	public int getPageNo();
	
	public int getPageSize();
	
	public int getPrePage();
	
	public int getNextPage();
	
	public boolean isFirstPage();
	
	public boolean isLastPage();
	
}
