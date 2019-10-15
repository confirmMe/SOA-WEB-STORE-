package com.taotao.search.dao;

import org.apache.solr.client.solrj.SolrQuery;

import com.taotao.common.pojo.SearchResult;

public interface ItemSearchDao {
	  SearchResult ItemSearchByQuery(SolrQuery  query) throws Exception;

}
