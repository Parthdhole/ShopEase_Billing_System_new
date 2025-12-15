package com.shop.ease.Controller;


import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shop.ease.io.CategoryRequest;
import com.shop.ease.io.CategoryResponse;
import com.shop.ease.service.CategoryService;
import com.shop.ease.service.impl.CategoryServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor

public class CategoryController {

	private final CategoryService categoryService;
	@PostMapping("/admin/categories")
	@ResponseStatus(HttpStatus.CREATED)
	public CategoryResponse addCategory(@RequestPart("category") String categoryString,
			@RequestPart("file") MultipartFile file) {
		ObjectMapper ObjectMapper= new ObjectMapper();
		CategoryRequest request=null;
		try {
			request = ObjectMapper.readValue(categoryString, CategoryRequest.class);
			return categoryService.addCategory(request,file);
		}catch(Exception ex){
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Exception occured While parsing the json"+ex.getMessage());
			
			
		}

		
	}
	
	@GetMapping()
	public List<CategoryResponse> FetchCategory(){
        return	categoryService.read();
	}
	@DeleteMapping("/admin/categories/{categoryId}")
	public void remove(@PathVariable String categoryId) {
		try{
			categoryService.delete(categoryId);
		}
		catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found: " +e.getMessage());
			
		}
		
	}
}

