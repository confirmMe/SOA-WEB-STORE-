package com.taotao.search.service;

import com.taotao.common.pojo.SearchResult;

public interface ItemSearchService {
	SearchResult ItemSearchByQuery(String queryString,Integer  page ,Integer rows) throws Exception;
}
