package com.taotao.service;

 
 

import java.util.List;

import com.taotao.common.pojo.EUDataCat;
 

public interface ItemCatService {
    List<EUDataCat> getItemCats(long parentId);
}
