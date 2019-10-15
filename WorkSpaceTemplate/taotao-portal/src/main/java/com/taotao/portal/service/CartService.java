package com.taotao.portal.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.portal.pojo.CartItem;

public interface CartService {
     TaotaoResult addCartItem(Long id,Long num,HttpServletRequest request,HttpServletResponse response,Integer updateFlag);
     List<CartItem> getCartItemList(HttpServletRequest request);
      
     
}
