package com.msg.util;

/**
 * 分页工具类
 * @author WebDev
 *
 */
public class PageUtil {

	//当前页码
	private int currentPage;
	//每页显示数据量，给定默认值5
	private int pageSize = 5;
	//总页数 = 总数据量 % 每页显示数据量 == 0 ?  总数据量 /每页显示数据量: 总数据量 /每页显示数据量+1
	private int totalPage;
	//总数据量
	private int totalNum;
	
	//起始偏移量-本次不写
	
	//set、get
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getTotalNum() {
		return totalNum;
	}
	/**
	 * 设置总数据量
	 * @param totalNum
	 */
	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
		//设置分页数
		//总页数 = 总数据量 % 每页显示数据量 == 0 ?  总数据量 /每页显示数据量: 总数据量 /每页显示数据量+1
		this.totalPage = totalNum%pageSize ==0 ? totalNum/pageSize:totalNum/pageSize+1;
		
	}
	
	
	
	
	
	
}
