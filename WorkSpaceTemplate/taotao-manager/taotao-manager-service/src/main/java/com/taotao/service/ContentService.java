package com.taotao.service;

import java.util.List;

import com.taotao.common.pojo.EUDataCat;
import com.taotao.common.pojo.EUDataResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbContent;

public interface ContentService {
   List<EUDataCat> catalogList(Long parentId);
   TaotaoResult  ContentCatSave(Long parentId,String name);
   TaotaoResult    ContentCatUpdate(Long id,String name);
   TaotaoResult ContentCatDelete( Long id);
   EUDataResult showContentList(Long categoryId ,int page,int rows);
   
   TaotaoResult AddContent(TbContent content);
   TaotaoResult EditContent(TbContent content);
   TaotaoResult DeleteContent(List<Long> list);
}
