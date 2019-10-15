package com.taotao.common.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * <p>
 * Title: ListUtils
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author 阎述伟
 * @date 2018年12月8日上午10:58:48
 * @version 1.0
 */

public class ListUtils {

	public static List<Long> IdsToList(String ids) {
		// 将前端返回的ids字符串封装成Long类型的List
		// ids标准格式为:"1,2,3,4"
		// 用于批量操作
		String[] ParamIds = ids.split(",");
		List<Long> list = new ArrayList<>();
		for (String ParamId : ParamIds) {
			list.add(Long.parseLong(ParamId));
		}
		return list;
	}

	public static boolean isEmptyString(String cs) {
		// 判断字符串是否为空。空返回true 非空返回false
		// " 1 1 " false
		// " " true
		// "" true
		int strLen;
		if (cs == null || (strLen = cs.length()) == 0) {
			return true;
		}
		for (int i = 0; i < strLen; i++) {
			if (cs.charAt(i) != " ".charAt(0)) {
				return false;
			}
		}
		return true;
	}

	public static boolean isEmptyList(List list) {
		// 判断队列中的元素是否为空。空返回true 非空返回false
		if (list != null & list.size() > 0) {
			return false;
		} else {
			return true;
		}
	}

	public static String toSoloString(String list) {
		// 返回用，分隔的字符串的首个字符
		// 1,2,3 ->1
		// <p>Title: toSoloString</p>
		// <p>Description: </p>
		if (list != null) {
			return list.split(",")[0];
		} else {
			return "";
		}
	}

}
