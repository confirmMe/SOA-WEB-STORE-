package com.taotao.portal.controller;


 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.SearchResult;
import com.taotao.portal.pojo.ItemInfo;
import com.taotao.portal.service.ItemService;
import com.taotao.portal.service.SearchService;
import com.taotao.portal.service.ShowAdverService;
 

@Controller
public class ItemController {
	@Value("${BigAdver}")
	private String BigAdverId;
	
 	@Autowired
    private ShowAdverService adverService;
	
	@Autowired
	private SearchService searchService;
	@Autowired
	private ItemService itemService;
	@RequestMapping("/index")
	public String load(Model model){	
		//加载主页面
		model.addAttribute("ad1", adverService.ShowService(BigAdverId));	
		return "index";
	}
	@RequestMapping("/search")
	public String search(String queryString,
			             @RequestParam(defaultValue="1") Integer page,
			             @RequestParam(defaultValue="60") Integer rows,
			             Model model) throws Exception{
		//加载搜索服务
		queryString=new String(queryString.getBytes("iso8859-1"), "utf-8");
		 
		SearchResult result=searchService.SearchFromSolr(queryString, page,rows);
		
		model.addAttribute("totalPages", result.getPageCount());
		model.addAttribute("page", page);		
		model.addAttribute("itemList", result.getItemList());
		model.addAttribute("query",queryString);
		return "search";
	}
	@RequestMapping("/item/{id}")
	public String item(@PathVariable Long id,Model model){
		ItemInfo info= itemService.getBaseInfo(id);
		model.addAttribute("item", info);
		return "item";
	}
	
	@RequestMapping(value="/item/desc/{id}", produces=MediaType.TEXT_HTML_VALUE+";charset=utf-8")
	@ResponseBody
	public String desc(@PathVariable long id){
		return itemService.getItemDesc(id);
	}
	
	@RequestMapping(value="/item/param/{id}",produces=MediaType.TEXT_HTML_VALUE+";charset=utf-8")
	@ResponseBody	 
	public String param(@PathVariable long id){
		return itemService.getItemParam(id);
	}
}
