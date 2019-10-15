package com.taotao.rest.service.Impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.utils.JsonUtils;
import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentExample;
import com.taotao.pojo.TbContentExample.Criteria;
import com.taotao.rest.dao.JedisClient;
import com.taotao.rest.service.ItemLoadService;
@Service
public class ItemLoadServiceImpl implements ItemLoadService {
    @Autowired
    private TbContentMapper contentMapper;
    @Autowired
    private JedisClient client;
    
    @Value("${INDEX_CONTENT_REDIS_KEY}")
    private String hashKey;
    @Value("${REDIS-BUTTON}")
    private Integer flag;
    
	public List<TbContent> ItemLoadList(Long contentCid) {
		
		    //对接口进行扫描，将自动对接口的每个实现类进行扫描，找不到javabean或者找到多于一个javabean都将会报错
            if(flag==1){
		    try {
		 	//从redis中成功找到数据    	    
			String okResult=client.hget(hashKey, contentCid.toString());
			if(!StringUtils.isBlank(okResult)){
			//判断json字符串的值是否是空			
			List<TbContent> okList=JsonUtils.jsonToList(okResult, TbContent.class);
			return okList;
			}
		    } catch (Exception e) {
			System.out.println("test");
		    }
            }
		TbContentExample example=new TbContentExample();
		Criteria criteria=example.createCriteria();
		criteria.andCategoryIdEqualTo(contentCid);
		List<TbContent> list=contentMapper.selectByExampleWithBLOBs(example);
		 if(flag==1){
	    	try {
	    	//从redis中获取数据失败
	    	//向redis中写入数据
			String failResult=JsonUtils.objectToJson(list);		
		    client.hset(hashKey, contentCid.toString(), failResult);	
		    } catch (Exception e) {
		    	System.out.println("test");
		    }
		 }
	    return list;
		}
	@Override
	public void updateRedis(Long contentCid) {
		//当后台管理系统更新了数据时，将redis中存储的数据删除，完成数据的同步
		client.hdel(hashKey, contentCid.toString());
	}

}
