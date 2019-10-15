package com.taotao.portal.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


import com.taotao.portal.pojo.CartItem;
import com.taotao.portal.pojo.Order;
import com.taotao.portal.service.CartService;
import com.taotao.portal.service.OrderService;

@Controller
@RequestMapping("/order")
public class OrderController {
	@Autowired 
	private CartService cartService;
	@Autowired
    private OrderService orderService;

	@RequestMapping("/order-cart")
	public String createOrder(Model model,HttpServletRequest request){
		
		List<CartItem> list=cartService.getCartItemList(request);
		model.addAttribute("cartList", list);
		return "order-cart";
	}
	@RequestMapping("/create")
	public String create(Order order,Model model){
		Long result=orderService.createOrder(order);
		if(result!=0){
			//返回值为0时说明订单创建错误，否则返回订单号
			model.addAttribute("orderId", result);
			model.addAttribute("payment", order.getPayment());
			//返回当前时间向后推三天的时间，并以格式"yyyy-MM-dd"返回
			model.addAttribute("date", new DateTime().plusDays(3).toString("yyyy-MM-dd"));
			return "success";
		}else{
			return "error";
		}
	}
}
