package com.taotao.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.ListUtils;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbItem;
import com.taotao.service.ContentService;
import com.taotao.service.ItemService;

@Controller
@RequestMapping("/rest")
public class RestController {
	@Autowired
	private ItemService itemService;

	
	@RequestMapping("/item/query/item/desc/{id}")
	@ResponseBody
	public TaotaoResult ShowDesc(@PathVariable Long id){
		//在修改商品中回显描述数据
		
		return itemService.ShowDesc(id);
	    
	}
	@RequestMapping("/item/param/item/query/{id}")
	@ResponseBody
	public TaotaoResult ShowParam(@PathVariable Long id){
		//路径也可以这样写@PathVariable(value="id") Long id
		//在修改界面中回显商品规格信息
		return itemService.ShowParam(id);
	}
	@RequestMapping("/item/update")
	@ResponseBody
	public TaotaoResult ItemUpdate(TbItem item ,String desc,String itemParams,Long itemParamId ){
		return itemService.ItemUpdate(item, desc, itemParams, itemParamId);
		
	}
	@RequestMapping("/item/delete")
	@ResponseBody
	public TaotaoResult ItemDelete(String ids){
		//根据商品ID删除多个商品
		List<Long> params=ListUtils.IdsToList(ids);
		return itemService.ItemDelete(params);
	}
	@RequestMapping("/item/instock")
	@ResponseBody
	public TaotaoResult ItemInstock(String ids){
		//根据商品ID上架多个商品
		List<Long> params=ListUtils.IdsToList(ids);
		return itemService.ItemInstock(params);
	}
    @RequestMapping("/item/reshelf")
    @ResponseBody
    public TaotaoResult ItemReshelf(String ids){
		//根据商品ID下架多个商品
		List<Long> params=ListUtils.IdsToList(ids);
		return itemService.ItemReshelf(params);
	}
    
	

}
