package com.shop.ease.service;



import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.shop.ease.io.CategoryRequest;
import com.shop.ease.io.CategoryResponse;

public interface CategoryService {
	
 public CategoryResponse addCategory(CategoryRequest request,MultipartFile file);
 public List<CategoryResponse> read();
 public void delete(String categoryId);
 
}

