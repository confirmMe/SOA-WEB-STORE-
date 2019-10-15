package com.taotao.search.service;

import org.apache.solr.client.solrj.SolrQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.search.dao.ItemSearchDao;
import com.taotao.common.pojo.SearchResult;
@Service
public class ItemSearchServiceImpl implements ItemSearchService{
    @Autowired
	private ItemSearchDao dao;
	public SearchResult ItemSearchByQuery(String queryString, Integer page, Integer rows) throws Exception {
		 
		SolrQuery query=new SolrQuery();
		query.setQuery(queryString);
		query.setRows(rows);
		query.setStart((page-1)*rows);
		
		//设置默认搜索域
		query.set("df", "item_keywords");
		
		//设置高亮
		query.setHighlight(true);
		query.addHighlightField("item_title");
		query.setHighlightSimplePre("<em style=\"color:red\">");
		query.setHighlightSimplePost("</em>");
		
		SearchResult result=dao.ItemSearchByQuery(query);
		result.setCurPage(page);
		
		//计算总页数
		long num=result.getRecordCount();
 
		long pageCount=num/rows;
 
		if(num%rows>0){
			pageCount++;
		}
 
		result.setPageCount(pageCount);
	 
		return result;
	}

}
