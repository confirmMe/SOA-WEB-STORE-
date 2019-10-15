package test;

import java.util.ArrayList;
import java.util.List;
/**
 * 
 * @author	阎述伟
 * @date	2019年1月14日下午4:33:03
 * @version 1.0
 */


public class MyStringUtils {
    public static boolean isEmptyString(String cs) {
    	//判断字符串是否为空。空返回true 非空返回false
    	//"  1  1  " false
    	//"   "      true
    	//""         true
        int strLen;
        if (cs == null || (strLen = cs.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if (cs.charAt(i)!=" ".charAt(0)) {
                return false;
            }
        }
        return true;
    }
    public static boolean isEmptyList(List list){
    	//判断队列中的元素是否为空。空返回true 非空返回false
    	if(list!=null&list.size()>0){
    		return false;
    	}else{
    		return true;
    	}
    }
    public  static List<Long> IdsToList(String ids){
		//将前端返回的ids字符串封装成Long类型的List
		//ids标准格式为:"1,2,3,4"
	    //用于批量操作
		String[] ParamIds=ids.split(",");
		List<Long> list=new ArrayList<>();
		for(String ParamId:ParamIds){
			list.add(Long.parseLong(ParamId));
		}
		return list;
	}
}
