package com.taotao.portal.service;

import com.taotao.portal.pojo.ItemInfo;

public interface ItemService {
    ItemInfo getBaseInfo(Long id);
    String getItemDesc(Long id);
    String getItemParam(Long id);
}
