package com.van.framework.core.orm.domain.vo;

import java.io.Serializable;

/** 
 * @className: SimplePage.java<br/>
 * @classDescription: none Description here<br/>
 * @createTime: 2015年4月29日
 * @author Van
 */
public abstract class SimplePage implements Paginable,Serializable {
	private static final long serialVersionUID = 7067718555671380304L;

	protected static final int DEF_COUNT = 20;
	protected int totalCount = 0;
	protected int pageSize = 15;
	protected int pageNo = 1;
	protected String sort = null;
	protected String dir = "ASC";
	
	
	
	public SimplePage() { }
	

	public SimplePage(int pageNo, int pageSize, int totalCount) {
		this.setPageNo(pageNo);
		this.setPageSize(pageSize);
		this.setTotalCount(totalCount);
		this.adjustPageNo();
	}


	private void adjustPageNo() {
		if(1 == this.pageNo) 
			return;
		int tp = this.getTotalPage();
		if(this.pageNo > tp)
			this.pageNo = tp;
	}


	@Override
	public int getTotalCount() {
		return this.totalCount;
	}

	@Override
	public int getTotalPage() {
		int totalPage = this.totalCount / this.pageSize;
		if(0 == totalPage || (this.totalCount % this.pageSize != 0))
			++totalPage;
		return totalPage;
	}

	@Override
	public int getPageNo() {
		return this.pageNo;
	}

	@Override
	public int getPageSize() {
		return this.pageSize;
	}

	@Override
	public int getPrePage() {
		if(isFirstPage())
			return this.pageNo;
		
		return this.pageNo - 1;
	}

	@Override
	public int getNextPage() {
		if(isLastPage())
			return this.pageNo;
		
		return this.pageNo + 1;
	}

	@Override
	public boolean isFirstPage() {
		return this.pageNo <= 1;
	}

	@Override
	public boolean isLastPage() {
		return this.pageNo >= this.getTotalPage();
	}

	public void setPageNo(int pageNo) {
		if(pageNo < 1)
			this.pageNo = 1;
		else
			this.pageNo = pageNo;
	}


	public String getSort() {
		return sort;
	}


	public void setSort(String sort) {
		this.sort = sort;
	}


	public String getDir() {
		return dir;
	}


	public void setDir(String dir) {
		this.dir = dir;
	}


	public void setTotalCount(int totalCount) {
		if(totalCount <= 0){
			this.totalCount = 0;
		} else{
			this.totalCount = totalCount;
		}
	}


	public void setPageSize(int pageSize) {
		if(pageSize < 1)
			this.pageSize = DEF_COUNT;
		else
			this.pageSize = pageSize;
	}

}
