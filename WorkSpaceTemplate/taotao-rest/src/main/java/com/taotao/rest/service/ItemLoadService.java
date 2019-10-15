package com.taotao.rest.service;

import java.util.List;
import com.taotao.pojo.TbContent;

public interface ItemLoadService {
	List<TbContent> ItemLoadList(Long contentCid);
	void updateRedis(Long contentCid);
}
