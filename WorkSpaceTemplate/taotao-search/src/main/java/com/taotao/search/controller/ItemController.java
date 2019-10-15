package com.taotao.search.controller;

 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.pojo.SearchResult;
import com.taotao.search.service.ItemSearchService;
import com.taotao.search.service.ItemService;

@Controller
public class ItemController {

	@Autowired
	private ItemService itemService;
	@Autowired
	private ItemSearchService itemSearchService;
	
	@RequestMapping("/manager/importall")
	@ResponseBody 
	private TaotaoResult ItemADD() throws Exception{
		//导入索引
		return  itemService.addItemService();
	}
	
	@RequestMapping("/query")
	@ResponseBody 
	private SearchResult ItemSearch(
		//查询索引
			String queryString 
			,Integer page
			,Integer rows) throws Exception{
		
		queryString=new String(queryString.getBytes("iso8859-1"), "utf-8");
		
		//解决get请求乱码
		return itemSearchService.ItemSearchByQuery(queryString, page, rows);
	}
	
	
}
