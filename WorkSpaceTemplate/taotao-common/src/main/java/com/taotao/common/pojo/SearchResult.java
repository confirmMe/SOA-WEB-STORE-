package com.taotao.common.pojo;

import java.util.List;

public class SearchResult {

	//商品列表
	private List<ItemSearch> itemList;
	//总记录数
	private long recordCount;
	//总页数
	private long pageCount;
	//当前页
	private long curPage;
	public List<ItemSearch> getItemList() {
		return itemList;
	}
	public void setItemList(List<ItemSearch> itemList) {
		this.itemList = itemList;
	}
	public long getRecordCount() {
		return recordCount;
	}
	public void setRecordCount(long recordCount) {
		this.recordCount = recordCount;
	}
	public long getPageCount() {
		return pageCount;
	}
	public void setPageCount(long pageCount) {
		this.pageCount = pageCount;
	}
	public long getCurPage() {
		return curPage;
	}
	public void setCurPage(long curPage) {
		this.curPage = curPage;
	}
	
}