package com.shop.ease.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {
	//it return the image url and accept multipart file as a prameter
	 String uplodaFile(MultipartFile file);
		
	boolean deletrFile(String imgurl);
	

}

