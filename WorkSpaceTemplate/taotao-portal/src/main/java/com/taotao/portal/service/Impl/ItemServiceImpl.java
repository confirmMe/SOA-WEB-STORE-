package com.taotao.portal.service.Impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.HttpClientUtil;
import com.taotao.common.utils.JsonUtils;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.portal.pojo.ItemInfo;
import com.taotao.portal.service.ItemService;
@Service
public class ItemServiceImpl implements ItemService{
    @Value("${ITEM-BASE-IP}")
    private String baseInfoIp;
    @Value("${ITEM-DESC-IP}")
    private String itemDescIp;
    @Value("${ITEM-PARAM-IP}")
    private String itemParam;
    
	public ItemInfo getBaseInfo(Long id) {
 
		
		String json=HttpClientUtil.doGet(baseInfoIp+id.toString());
		TaotaoResult result=TaotaoResult.formatToPojo(json, ItemInfo.class);
		if(result.getStatus()==200){
			ItemInfo info=(ItemInfo) result.getData();
			String[] images=info.getImage().split(",");
			info.setImages(images);
			return info;
 
		}else{
		return null;
		}
	}
 
	public String getItemDesc(Long id) {
		String json=HttpClientUtil.doGet(itemDescIp+id.toString());
		TaotaoResult result=TaotaoResult.formatToPojo(json, TbItemDesc.class);
		if(result.getStatus()==200){
			TbItemDesc desc=(TbItemDesc) result.getData();
			return desc.getItemDesc();
		}else{
			return null;
		}
	}

	@SuppressWarnings("rawtypes")
	public String getItemParam(Long id) {
		String json=HttpClientUtil.doGet(itemParam+id.toString());
	    TaotaoResult result=  TaotaoResult.formatToPojo(json, TbItemParamItem.class);
		if(result.getStatus()==200){
			TbItemParamItem param=(TbItemParamItem) result.getData();
			
			String paramData=param.getParamData();
			List<Map> jsonList = JsonUtils.jsonToList(paramData, Map.class);
			StringBuffer sb = new StringBuffer();
			sb.append("<table cellpadding=\"0\" cellspacing=\"1\" width=\"100%\" border=\"0\" class=\"Ptable\">\n");
			sb.append("    <tbody>\n");
			for(Map m1:jsonList) {
				sb.append("        <tr>\n");
				sb.append("            <th class=\"tdTitle\" colspan=\"2\">"+m1.get("group")+"</th>\n");
				sb.append("        </tr>\n");
				List<Map> list2 = (List<Map>) m1.get("params");
				for(Map m2:list2) {
					sb.append("        <tr>\n");
					sb.append("            <td class=\"tdTitle\">"+m2.get("k")+"</td>\n");
					sb.append("            <td>"+m2.get("v")+"</td>\n");
					sb.append("        </tr>\n");
				}
			}
			sb.append("    </tbody>\n");
			sb.append("</table>");
			//返回html片段
			return sb.toString();
		}else{
	    return null;
		}
	}

}
