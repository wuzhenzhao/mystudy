package com.wuzz.demo.core.util;

import java.io.Serializable;
import java.util.List;

public class PageBean<T> implements Serializable{
	
	public PageBean(List<T> list ,Long totalCount){
		this.list = list ;
		this.totalCount = totalCount ;
	}
	
	public PageBean(Integer pageNo, Integer pageSize, List<T> list, Long totalCount) {
		this.pageNo = pageNo;
		this.pageSize = pageSize;
		this.list = list;
		this.totalCount = totalCount;
	}

	public PageBean(){
		
	}
	private Integer pageNo;
	
	private Integer pageSize;
	
	private List<T> list ;
	
	private Long totalCount ;
	
	//private Integer pageNo ;

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public Long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}


	

}
