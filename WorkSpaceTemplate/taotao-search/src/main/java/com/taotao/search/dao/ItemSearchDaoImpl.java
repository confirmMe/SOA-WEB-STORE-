package com.taotao.search.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.taotao.common.pojo.ItemSearch;
import com.taotao.common.pojo.SearchResult;
@Repository
public class ItemSearchDaoImpl implements ItemSearchDao {

 	@Autowired
 	private SolrServer server;
	
	@SuppressWarnings("unchecked")
	public SearchResult ItemSearchByQuery(SolrQuery query) throws Exception {
		SearchResult result=new SearchResult();
		List<ItemSearch> list=new ArrayList<>();
		
	    
		QueryResponse queryResponse=server.query(query);
		SolrDocumentList documentList=queryResponse.getResults();
		//设置总记录
		result.setRecordCount(documentList.getNumFound());
		//获取高亮
		Map<String, Map<String, List<String>>> highlight=queryResponse.getHighlighting();
		 
		for(SolrDocument document:documentList){
			ItemSearch itemSearch=new ItemSearch();
			
			//获取目标高亮list
			List<String> highList=highlight.get(document.get("id")).get("item_title");
			
			if(highList!=null&&highList.size()>0){
				itemSearch.setTitle((String)highList.get(0));
			}else{
				itemSearch.setTitle((String)document.get("item_title"));
			}
			
			itemSearch.setId((String) document.get("id"));
			itemSearch.setImage((String) document.get("item_image"));
			itemSearch.setItem_desc((String) document.get("item_desc"));
			itemSearch.setCatagery_name((String) document.get("item_category_name"));
			itemSearch.setPrice((long) document.get("item_price"));
			itemSearch.setSell_point((String) document.get("item_sell_point"));
			list.add(itemSearch);
			
		}
		result.setItemList(list);
		return result;
	}

}
