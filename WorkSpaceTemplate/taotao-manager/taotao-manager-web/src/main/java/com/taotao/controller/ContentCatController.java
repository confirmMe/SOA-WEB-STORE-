package com.taotao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sun.jdi.IntegerType;
import com.taotao.common.pojo.EUDataCat;
import com.taotao.common.pojo.EUDataResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.ListUtils;
import com.taotao.pojo.TbContent;
import com.taotao.service.ContentService;
@Controller
@RequestMapping("/content")
public class ContentCatController {
    @Autowired
	private ContentService contentService;
	
	@RequestMapping("/category/list")
	@ResponseBody
	public List<EUDataCat> ContentCatList(@RequestParam (value="id",defaultValue="0") Long parentId){
		return contentService.catalogList(parentId);
	}
	@RequestMapping("/category/create")
	@ResponseBody
	public TaotaoResult ContentCatSave(Long parentId,String name){
		return contentService.ContentCatSave(parentId, name);
	}
	@RequestMapping("category/update")
	@ResponseBody
	public TaotaoResult ContentCatUpdate(Long id,String name){
		return contentService.ContentCatUpdate(id, name);
	}
	@RequestMapping("/category/delete")
	@ResponseBody
	public TaotaoResult ContentCatDelete(Long id){
		return contentService.ContentCatDelete(  id);
		 
	}
	@RequestMapping("/query/list")
	@ResponseBody
	public EUDataResult showContentList(Long categoryId ,int page,int rows){
		return contentService.showContentList(categoryId, page, rows);
	}
	@RequestMapping("/save")
	@ResponseBody
	public TaotaoResult AddContent(TbContent content){
		return contentService.AddContent(content);
		
	}
	@RequestMapping("/edit")
    @ResponseBody
    public TaotaoResult ContentEdit(TbContent content){
    	return contentService.EditContent(content);
    }
	@RequestMapping("/delete")
	@ResponseBody
	public TaotaoResult ContentDelete(String ids){
		List<Long> list=ListUtils.IdsToList(ids);
		return contentService.DeleteContent(list);
	}
	

}
