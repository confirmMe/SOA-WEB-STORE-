package com.taotao.service.Impl;

 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.EUDataCat;
import com.taotao.mapper.TbItemCatMapper;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemCatExample;
import com.taotao.pojo.TbItemCatExample.Criteria;
import com.taotao.service.ItemCatService;
@Service
public class ItemCatServiceImpl implements ItemCatService {
	
	
    @Autowired
	private TbItemCatMapper catMapper;
	@SuppressWarnings("unchecked")
	public List<EUDataCat> getItemCats(long parentId) {
		// TODO Auto-generated method stub
		
		
		TbItemCatExample  catExample=new TbItemCatExample();
		Criteria criteria=catExample.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		List<TbItemCat> list=(List<TbItemCat>) catMapper.selectByExample(catExample);
		
		List dataList=new ArrayList<>();
	    for(TbItemCat cat :list){
	    	EUDataCat dataCat=new EUDataCat();
	    	dataCat.setId(cat.getId());
	    	dataCat.setText(cat.getName());
	    	dataCat.setState(cat.getIsParent()?"closed":"open");
	    	dataList.add(dataCat);
	    }
	    
	    return dataList;
	    
 
	    
 
	    
	    
	}

}
