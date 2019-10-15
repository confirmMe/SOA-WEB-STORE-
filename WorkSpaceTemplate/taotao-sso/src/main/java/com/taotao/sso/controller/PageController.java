package com.taotao.sso.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
public class PageController {

	@RequestMapping("/")
	public String show(String redirect,Model model){
		//用户登录完成后跳转回之前的页面，需要把之前页面的URL写入参数
		model.addAttribute("redirect", redirect);
		return "login";
	}                 
	@RequestMapping("/page/register")
	public String register(){
		return "register";
	}
 
}
