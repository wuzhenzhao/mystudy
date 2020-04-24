package com.wuzz.demo.core;



public class PageParam {


	/**
	 * 每页显示几条
	 */
	private Integer pageSize=10;


	/**
	 * 第几页
	 */
	private Integer pageNum=1;


	public PageParam(Integer pageSize, Integer pageNum) {
		this.pageSize = pageSize;
		this.pageNum = pageNum;
	}


	public PageParam() {
	}


	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}
}
