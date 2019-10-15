package com.taotao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

 
@Controller
public class PageController {
	
    @RequestMapping("/")
    public String Show(){	
    	return "index";
    }
	@RequestMapping("/{page}")
	public String showpage(@PathVariable String page) {
		//=@PathVariable(value="page")  String page(只有一个变量，默认自动选择)
		//动态跳转页面，当controller层中有对应的requestmapping时优先调用，否则调用本方法从jsp包中查找
		return page;
	}
	
	 
	
}
