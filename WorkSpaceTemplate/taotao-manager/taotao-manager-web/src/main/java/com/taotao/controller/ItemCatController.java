package com.taotao.controller;

 

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EUDataCat;
 
import com.taotao.service.ItemCatService;

 

@Controller
@RequestMapping("/item/cat")
public class ItemCatController {
    
	@Autowired
	private ItemCatService catService;
	
 	@SuppressWarnings({ "rawtypes", "unchecked" })//屏蔽无关警告
	@ResponseBody
	@RequestMapping("/list")
	public  List<EUDataCat> catalogList(@RequestParam(value="id",defaultValue="0" ) long parentId ){
		
		List<EUDataCat> result= catService.getItemCats(parentId);
 
		return result;
	}
	
}
