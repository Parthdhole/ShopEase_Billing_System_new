package com.shop.ease.service.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.shop.ease.service.FileUploadService;

import lombok.RequiredArgsConstructor;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;
@Service
@RequiredArgsConstructor
public class FileUploadServiceImpl implements FileUploadService {
	@Value("${aws.bucket.name}")
	private  String bucketName;
	@Value("${aws.region}")
	private String region;

	
	
	private final S3Client s3client;
	@Override
	public String uplodaFile(MultipartFile file) {
	
	// it will give us file name extension
	String filenameExtension=file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")+1);
	//Then we need to generate the key for this filename
	String key=UUID.randomUUID().toString()+"."+filenameExtension;
	System.out.println("Region loaded from properties = " + region);

	try {
	PutObjectRequest putobjectRequest = PutObjectRequest.builder()
		    .bucket(bucketName)
			.key(key)
			.acl("public-read")
			.contentType(file.getContentType())
			.build();
	PutObjectResponse response = s3client.putObject(putobjectRequest,RequestBody.fromBytes(file.getBytes()));
	 if(response.sdkHttpResponse().isSuccessful()) {
		 return "https://" + bucketName + ".s3." + region+ ".amazonaws.com/" + key;
	 }
	 else {
		 throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"An error occure while uploding the file ");
	 }
	} catch (Exception e) {
	 throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"An error occure while uploding the file ");

	}
	
	
	}

	@Override
	public boolean deletrFile(String imgurl) {
		String Filename=imgurl.substring(imgurl.lastIndexOf("/")+1);
		DeleteObjectRequest delteDeleteObjectRequest= DeleteObjectRequest.builder()
				.bucket(bucketName)
				.key(Filename)
				.build();
		s3client.deleteObject(delteDeleteObjectRequest);
		return true;
	}
	

}
/**
 * Create S3 bucket → storage

Add AWS keys → authentication

Create config → make S3Client object

Create service interface → define functions

Create implementation → write logic (upload + delete)
 */

