package com.taotao.service;

import org.springframework.web.multipart.MultipartFile;

import com.taotao.common.pojo.DataPicture;

public interface PictureService {

	
	  DataPicture uploadPicture(MultipartFile file);
}
