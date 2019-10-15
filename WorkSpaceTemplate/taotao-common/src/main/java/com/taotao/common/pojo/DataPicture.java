package com.taotao.common.pojo;
/**
 * 用于返回前端的图片上传功能需要的信息
 * <p>Title: DataPicture</p>
 * <p>Description: </p>
 * @author	阎述伟
 * @date	2018年12月5日下午4:37:34
 * @version 1.0
 * 成功:error=0 url为图片传输到ImageServer的绝对路径
 * 失败:error=1 message显示提示信息
 */
public class DataPicture {
      private  int error;
      private String message;
      private String url;
	public int getError() {
		return error;
	}
	public void setError(int error) {
		this.error = error;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
}
