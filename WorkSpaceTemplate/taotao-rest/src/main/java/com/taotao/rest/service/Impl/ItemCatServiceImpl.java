package com.taotao.rest.service.Impl;

 
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.utils.JsonUtils;
import com.taotao.mapper.TbItemCatMapper;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemCatExample;
import com.taotao.pojo.TbItemCatExample.Criteria;
import com.taotao.rest.dao.JedisClient;
import com.taotao.rest.pojo.CatNode;
import com.taotao.rest.pojo.CatResult;
import com.taotao.rest.service.ItemCatService;

@Service
public class ItemCatServiceImpl implements ItemCatService {
    @Autowired
	private TbItemCatMapper catMapper;
    @Autowired
    private JedisClient client;
 
    @Value("${INDEX_CAT_REDIS_KEY}")
    private String catKey;
    @Value("${INDEX_CAT_REDIS_HASH_KEY}")
    private String catFlag;
    @Value("${REDIS-BUTTON}")
    private Integer flag;
	public String ShowItemCat() {
		
		if(flag==1){
		try {
			//如果redis中找到了需要的数据
			//默认的key为"catFlag"
			String testResult=client.hget(catKey, catFlag);
			if(!StringUtils.isBlank(testResult)){		
				return testResult;
			}
		} catch (Exception e) { 
			System.out.println("fail");
		}
		
		}
		CatResult catResult=new CatResult();
		catResult.setData(getItemCat((long) 0));
		String result=JsonUtils.objectToJson(catResult);
		
		if(flag==1){
		try {
			client.hset(catKey, catFlag, result);
		} catch (Exception e) {		
			System.out.println("fail");
		}
		}
		
		return result;
		 
	}
    public List getItemCat(Long parentId){
    	int index=0;
    	List result=new ArrayList<>();
    	TbItemCatExample example=new TbItemCatExample();
    	Criteria criteria=example.createCriteria();
    	criteria.andParentIdEqualTo(parentId);
    	List<TbItemCat> list=catMapper.selectByExample(example);
    	for(TbItemCat cat:list){
    		if(cat.getIsParent()){
    		CatNode catNode=new CatNode();
    		if(parentId==0){
    			catNode.setN("<a href='/products/"+cat.getId()+".html'>"+cat.getName()+"</a>");
			} else {
				catNode.setN(cat.getName());
			}
    		catNode.setU("/products/"+cat.getId()+".html");
    		catNode.setI(getItemCat(cat.getId()));
    		result.add(catNode);
    		
    		index++;
    		if(parentId==0&&index==14)
    			break;
    	}
    	else{
    		result.add("/products/"+cat.getId()+".html|" + cat.getName());
    	}
    }
    	return result;
    	
	}

}
