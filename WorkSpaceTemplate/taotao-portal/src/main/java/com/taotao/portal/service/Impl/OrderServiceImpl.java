package com.taotao.portal.service.Impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.HttpClientUtil;
import com.taotao.common.utils.JsonUtils;
import com.taotao.portal.pojo.Order;
import com.taotao.portal.service.OrderService;
@Service
public class OrderServiceImpl implements OrderService {
    @Value("${CreateOrderIp}")
	private String OrderUrl;
	public Long createOrder(Order order) {
	   
		String json=JsonUtils.objectToJson(order);
		System.out.println(json);
		String jsonResult=HttpClientUtil.doPostJson(OrderUrl, json);

		TaotaoResult  result=JsonUtils.jsonToPojo(jsonResult, TaotaoResult.class);
		if(result.getStatus()==200){
			return ((Integer) result.getData()).longValue();
		}
		return 0l;
	}

}
