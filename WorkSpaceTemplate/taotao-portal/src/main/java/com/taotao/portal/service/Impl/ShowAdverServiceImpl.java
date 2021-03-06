package com.taotao.portal.service.Impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.taotao.common.utils.HttpClientUtil;
import com.taotao.common.utils.JsonUtils;
import com.taotao.pojo.TbContent;
import com.taotao.portal.service.ShowAdverService;
@Service
public class ShowAdverServiceImpl implements ShowAdverService {

	@Value("${LOadRestIp}")
	private String LOadRestIp;
	    
	@SuppressWarnings("unchecked")
	public String ShowService(String AdverId) {
		
		Map<String,String> map=new HashMap<>();
		map.put("contentCid", AdverId);
		
 
		//调用taotao-rest提供的服务
		//如果使用get也可以
		String resultGet= HttpClientUtil.doPost(LOadRestIp, map);
		
		
		
		//将json数据转换为pojo类	
	    List<TbContent> list=JsonUtils.jsonToList(resultGet, TbContent.class);
		
	 
		List<Map> resultList=new ArrayList<>();
		for(TbContent tbContent:list){
			Map mymap = new HashMap<>();
			mymap.put("src", tbContent.getPic());
			mymap.put("height", 240);
			mymap.put("width", 670);
			mymap.put("srcB", tbContent.getPic2());
			mymap.put("widthB", 550);
			mymap.put("heightB", 240);
			mymap.put("href", tbContent.getUrl());
			mymap.put("alt", tbContent.getSubTitle());
			resultList.add(mymap);
		}
		//将list转换为json数据
		String result=JsonUtils.objectToJson(resultList);	
		return result;	
		}
		
		
		
		 


}
