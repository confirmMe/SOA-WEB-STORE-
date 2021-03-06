package com.taotao.rest.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.taotao.pojo.TbContent;
import com.taotao.rest.service.ItemLoadService;

@Controller
@RequestMapping("/content")
public class ItemLoadController {
    @Autowired
	private ItemLoadService itemLoadService;
    
    @RequestMapping("/list")
    @ResponseBody
    public List<TbContent> ShowItemLoad(long contentCid) throws UnsupportedEncodingException{
    	//提供展示前台广告数据的服务
    	 return itemLoadService.ItemLoadList(contentCid);	 
    	 
    }
    
    @RequestMapping("/redisUpdate")
    public void updateRedis(Long contentCid){
    	
    	//在后台修改TbContent表后，删除redis中对应的数据，完成数据的同步的服务
    	itemLoadService.updateRedis(contentCid);
    }

    
}
