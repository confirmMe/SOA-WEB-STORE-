package com.taotao.rest.service;

import com.taotao.common.pojo.TaotaoResult;

public interface ItemService {
     TaotaoResult getItemInfo(Long id);
     TaotaoResult getItemDesc(Long id);
     TaotaoResult getItemParam(Long id);
}
