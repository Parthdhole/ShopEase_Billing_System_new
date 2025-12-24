package com.shop.ease.service.impl;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.shop.ease.entity.CategoryEntity;
import com.shop.ease.io.CategoryRequest;
import com.shop.ease.io.CategoryResponse;
import com.shop.ease.repository.CategoryRepository;
import com.shop.ease.repository.ItemRepository;
import com.shop.ease.service.CategoryService;
import com.shop.ease.service.FileUploadService;

import lombok.RequiredArgsConstructor;



@Service
@RequiredArgsConstructor
public  class CategoryServiceImpl implements CategoryService{

	/**
	 * our constructor look like this here
	 * public CategoryServiceImpl(CategoryRepository repository, FileUploadService fileUploadService) {
    this.repository = repository;
    this.fileUploadService = fileUploadService;
}v
	 */
	
	private final  CategoryRepository repository; 
	
	private final FileUploadService fileUploadService;
	
	private final ItemRepository itemRepository;
	@Override
	public CategoryResponse addCategory(CategoryRequest request,MultipartFile file) {
    String imgUrl=fileUploadService.uplodaFile(file);
	CategoryEntity newCategoryEntity=convertToEntity(request);
	newCategoryEntity.setImgUrl(imgUrl);
	newCategoryEntity =repository.save(newCategoryEntity); //we can store the data in database
	//coverting the values to the coverToResopnce
	return covertToResponse(newCategoryEntity);
	}

	private CategoryResponse covertToResponse(CategoryEntity newCategoryEntity) {
		// TODO Auto-generated method stub
		// how many count are present for particular category id
	Integer itemsCount=	itemRepository.countByCategoryId(newCategoryEntity.getId());
		return CategoryResponse.builder()
				 .catagoryId(newCategoryEntity.getCategoryId())
		         .name(newCategoryEntity.getName())
		         .description(newCategoryEntity.getDescription())
		         .bgColor(newCategoryEntity.getBgColor())
		         .imgUrl(newCategoryEntity.getImgUrl())
		         .createdAt(newCategoryEntity.getCreateAT())
		         .UpdatedAt(newCategoryEntity.getUpdatedAt())
		         .items(itemsCount)
		         .build();
		
	}

	private CategoryEntity convertToEntity(CategoryRequest request) {
	//builder path run to copy all values to CategoryEntity
	return	CategoryEntity.builder()
		.categoryId(UUID.randomUUID().toString())
		.name(request.getName())
		.description(request.getDescription())
		.bgColor(request.getBgColor())
		.build();
		
		
	
	}
/**
 * In the service layer, I first convert the incoming request into an entity using convertToEntity().
There I generate a UUID as categoryId and set all fields.
Then I save this entity into the database using repository.save().
After saving, I convert the saved entity into a response DTO using covertToResponse() and return it back to the controller.
 */

	@Override
	public List<CategoryResponse> read() {
		// TODO Auto-generated method stub
		return repository.findAll()
		                   .stream()
		                   .map(CategoryEntity->covertToResponse(CategoryEntity))
		                   .collect(Collectors.toList());
		                		   
		
		
	}
@Override
public void delete(String categoryId) {
	
	CategoryEntity existingcategory=repository.findByCategoryId(categoryId)
	.orElseThrow(()->new RuntimeException("Category not found :"+categoryId));
	fileUploadService.deletrFile(existingcategory.getImgUrl());
	repository.delete(existingcategory);
	
}

}

