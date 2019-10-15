package com.taotao.common.pojo;

import java.util.List;
//封装了查询列表时需要返回的数据
public class EUDataResult {
   private Long total;
   private List<?> rows;
public Long getTotal() {
	return total;
}
public void setTotal(Long total) {
	this.total = total;
}
public List<?> getRows() {
	return rows;
}
public void setRows(List<?> rows) {
	this.rows = rows;
}
   
}
