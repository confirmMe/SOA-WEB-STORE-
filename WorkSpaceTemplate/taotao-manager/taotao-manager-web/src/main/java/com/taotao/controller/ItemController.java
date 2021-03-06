package com.taotao.controller;

 
 


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.taotao.common.pojo.EUDataResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItem;
import com.taotao.service.ItemService;

@Controller
@RequestMapping("/item")
public class ItemController {
 
	@Autowired
	private ItemService itemService;
    
	@RequestMapping("/list")
	@ResponseBody
    public EUDataResult getItemList(int page ,int rows){
         //获取商品列表
		 EUDataResult dataResult=itemService.getItemList(page, rows);
		 return dataResult;
	}
	@RequestMapping("/save")
	//可以写成RequestMapping(value="/save",method=RequestMethod.POST)
	@ResponseBody
	public TaotaoResult addItem(TbItem item ,String desc,String itemParams) throws Exception{
		//添加商品
		TaotaoResult result=itemService.addItem(item,desc,itemParams);
		return result;
	}
	@RequestMapping("/param/list")
    @ResponseBody
    public EUDataResult getItemParamList(int page,int rows){
		//获取规格参数列表
		EUDataResult dataResult=itemService.getItemParam(page, rows);
		return dataResult;
	}
	@RequestMapping("/param/query/itemcatid/{itemCatId}")
    @ResponseBody
    public TaotaoResult paramShow(@PathVariable Long  itemCatId){
		//判断此类商品是否已经增加类目类
		//是，返回TaotaoResult.ok
		//否，返回TaotaoResult.ok(data)
	    return itemService.showIsParam(itemCatId);
	}
	@RequestMapping("/param/save/{itemCatId}")
	@ResponseBody
	public TaotaoResult addItemParam(@PathVariable Long itemCatId,String paramData){
		//添加商品类目信息,前端已经将数据封装好为json再将其转换为字符串,以符合数据库规范
		return itemService.addItemParam(itemCatId, paramData);
		
	}
	

	
	 
	 
}
