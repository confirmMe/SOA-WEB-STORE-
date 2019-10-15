package com.taotao.common.pojo;
/**
 * easyUI树形控件节点格式
 * @version 1.0
 * state如果不是叶子节点就是close，叶子节点就是open。Close的节点点击后会在此发送请求查询子项目。
 */
public class EUDataCat {

	private long id;
	private String text;
	private String state;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
}
