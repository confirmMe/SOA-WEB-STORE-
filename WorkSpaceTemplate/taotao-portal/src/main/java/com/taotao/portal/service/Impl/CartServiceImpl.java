package com.taotao.portal.service.Impl;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.CookieUtils;
import com.taotao.common.utils.HttpClientUtil;
import com.taotao.common.utils.JsonUtils;
import com.taotao.common.utils.ListUtils;
import com.taotao.pojo.TbItem;
import com.taotao.portal.pojo.CartItem;
import com.taotao.portal.service.CartService;
@Service
public class CartServiceImpl implements CartService {

	@Value("${ITEM-BASE-IP}")
	private String RedisUrl;
	@Value("${TT_CART}")
	private String TT_CART;
	public TaotaoResult addCartItem(Long id, Long num,HttpServletRequest request,HttpServletResponse response,Integer updateFlag) {
		
		/**
		 * 将选中的商品添加进购物车，如果购物车中已经存在当前商品，则只增加其数量，否则添加商品的信息
		 * 由于update操作和add操作还有delete操作类似，设置updateFlag为0时为delete操作为1时为add操作，为2时为update操作
		 */
		boolean flag=false;//判断cookie中有没有存在当前要存入数据的标志 ，false 无 true 有
		//取购物车中的商品列表
		List<CartItem> list=getCartItemList(request);

		if(!ListUtils.isEmptyList(list)){
		//如果当前购物车有商品
		for(CartItem cartData:list){
			//遍历购物车的商品
			if(id==cartData.getId()){
				//如果在购物车中已经存在该商品的信息
				flag=true;
				//置falg标志为true，跳过HttpClientUtil的操作
				if(updateFlag==1){
					//增加商品
				num=num+cartData.getNum();
				}
				if(updateFlag==0){
				list.remove(cartData);
				    //删除商品
				break;
				}
				cartData.setNum(num);	
				break;
				//购物车中不可能出现重复的商品，所以继续循环没有意义，跳出for循环
			}
		}
		}
		if(flag==false){
			//此时购物车为空或者购物车中没有当前商品信息
		String json=HttpClientUtil.doGet(RedisUrl+id);
            
		TaotaoResult result=TaotaoResult.formatToPojo(json, TbItem.class);
		if(result.getStatus()==200){
			//通过id获取商品信息
			TbItem tbItem=(TbItem) result.getData();
			CartItem cartItem=new CartItem();
			cartItem.setId(tbItem.getId());
			cartItem.setImage(ListUtils.toSoloString(tbItem.getImage()));//取第一张图片
			cartItem.setNum(num);
			cartItem.setPrice(tbItem.getPrice());
			cartItem.setTitle(tbItem.getTitle());
			list.add(cartItem);
			//将新的商品信息保存到list中
		}
		}
		String json=JsonUtils.objectToJson(list);
		//保存的始终是list中的信息
		CookieUtils.setCookie(request, response, TT_CART, json, true);
		//向cookie中写入更新后的购物车数据
		return TaotaoResult.ok();
	}
	public List<CartItem> getCartItemList(HttpServletRequest request){
		//从cookie中取出购物车的商品信息
		String cartJson=CookieUtils.getCookieValue(request, TT_CART, true);
		if(cartJson==null){
			return new ArrayList<>();
			//cookie中找不到购物车的信息则返回一个新的list否则会报出空指针异常
		}
		List<CartItem> list=JsonUtils.jsonToList(cartJson, CartItem.class);
		return list;
		
	}
 
	

 
}
