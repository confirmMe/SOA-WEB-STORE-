package com.taotao.service;

import java.util.List;

import org.springframework.aop.framework.AopConfigException;

import com.taotao.common.pojo.EUDataResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItem;

public interface ItemService {

	EUDataResult getItemList(int page, int rows);

	TaotaoResult addItem(TbItem item, String desc, String itemParams) throws Exception;

	EUDataResult getItemParam(int page, int rows);

	TaotaoResult showIsParam(Long itemCatId);

	TaotaoResult addItemParam(Long itemCatId, String paramData);

	TaotaoResult ShowDesc(Long id);

	TaotaoResult ShowParam(Long id);

	TaotaoResult ItemUpdate(TbItem item, String desc, String itemParams, Long itemParamId);

	TaotaoResult ItemDelete(List<Long> params);

	TaotaoResult ItemInstock(List<Long> params);

	TaotaoResult ItemReshelf(List<Long> params);
}
