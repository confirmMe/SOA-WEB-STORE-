package com.taotao.portal.pojo;
/**
 * 本POJO的意义是存储购物车中存放的商品信息
 * @author	阎述伟
 * @date	2019年1月16日下午4:15:11
 * @version 1.0
 */
public class CartItem {
	   private long id;
	   private String title;
	   private String image;
	   private long price;
	   private long num;
 
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public long getPrice() {
		return price;
	}
	public void setPrice(long price) {
		this.price = price;
	}
	public long getNum() {
		return num;
	}
	public void setNum(long num) {
		this.num = num;
	}
 
 
	
}
