package com.van.framework.core.orm.domain.vo;

import java.util.List;

/** 
 * @className: Pagination.java<br/>
 * @classDescription: none Description here<br/>
 * @createTime: 2015年4月29日
 * @author Van
 */
public class Pagination extends SimplePage {
	private static final long serialVersionUID = -2358575290784749659L;

	private List<?> list;

	public Pagination() { }
	
	public Pagination(int pageNo, int pageSize, int totalCount){
		super(pageNo, pageSize, totalCount);
	}
	
	public Pagination(int pageNo, int pageSize, int totalCount, List<?> list){
		this(pageNo, pageSize, totalCount);
		this.list = list;
	}

	public int getFirstResult(){
		return (this.pageNo - 1) * pageSize;
	}
	
	public List<?> getList() {
		return list;
	}

	public void setList(List<?> list) {
		this.list = list;
	}

	@Override
	public String toString() {
		StringBuilder sbd = new StringBuilder("Pagination[");
		sbd.append("pageNo=" + this.getPageNo())
			.append(",pageSize=" + this.getPageSize())
			.append(",totalPage=" + this.getTotalPage())
			.append(",totalCount=" + this.getTotalCount())
			.append(",sort=" + this.getSort())
			.append(",dir=" + this.getDir())
			.append("]");
		return sbd.toString();
	}
	
	
}
