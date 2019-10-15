package com.taotao.portal.service.Impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.ItemSearch;
import com.taotao.common.pojo.SearchResult;
import com.taotao.common.utils.HttpClientUtil;
import com.taotao.common.utils.JsonUtils;
import com.taotao.portal.service.SearchService;
@Service
public class SearchServiceImpl implements SearchService {
    @Value("${LoadSearchIp}")
	private String LoadSearchIp;
    
	public SearchResult SearchFromSolr(String queryString, Integer page,Integer rows) {
		 
		
		Map<String, String>  map=new HashMap<>();
		map.put("queryString", queryString);
		map.put("page", page.toString());
		map.put("rows", rows.toString());
        
		String result=HttpClientUtil.doGet(LoadSearchIp, map);
		
		SearchResult searchResult=JsonUtils.jsonToPojo(result, SearchResult.class);
		
		//只取第一张图片作为封面图片
		List<ItemSearch> list=searchResult.getItemList();
		for(ItemSearch itemSearch:list){
			if(itemSearch.getImage()!=null){
			String[] images=itemSearch.getImage().split(",");
			itemSearch.setImage(images[0]);
			}
		}
		
		return searchResult;
	}
}
