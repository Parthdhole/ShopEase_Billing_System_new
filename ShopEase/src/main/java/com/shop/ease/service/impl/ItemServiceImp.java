package com.shop.ease.service.impl;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.shop.ease.config.SecurityConfig;
import com.shop.ease.entity.CategoryEntity;
import com.shop.ease.entity.ItemEntity;
import com.shop.ease.io.ItemRequest;
import com.shop.ease.io.ItemResponce;
import com.shop.ease.repository.CategoryRepository;
import com.shop.ease.repository.ItemRepository;
import com.shop.ease.service.FileUploadService;
import com.shop.ease.service.ItemService;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class ItemServiceImp implements ItemService {

    private final SecurityConfig securityConfig;

	
	private final FileUploadService fileUploadService;
	
	private final CategoryRepository categoryRepository;
	
	private final ItemRepository itemRepository;

	@Override
	public ItemResponce add(ItemRequest request, MultipartFile file) {
		// TODO Auto-generated method stub
	  String imgUrl= fileUploadService.uplodaFile(file);
	  ItemEntity newItem= covertToEntity(request);
	  CategoryEntity existingCategory=categoryRepository.findByCategoryId(request.getCategoryId())
	  .orElseThrow(()->new RuntimeException("Category not found:"+request.getCategoryId()));
	  newItem.setCategory(existingCategory);
	  newItem.setImgUrl(imgUrl);
	  newItem=itemRepository.save(newItem);
	  return converToResponce(newItem);
	}

	private ItemResponce converToResponce(ItemEntity newItem) {
		// TODO Auto-generated method stub
		return ItemResponce.builder()
		.itemId(newItem.getItemId())
		.name(newItem.getName())
		 .description(newItem.getDescription())
		.imgUrl(newItem.getImgUrl())
		 .categoryName(newItem.getCategory().getName())
		 .categoryId(newItem.getCategory().getCategoryId())
		 .createdAt(newItem.getCreatedAt())
		 .updatedAt(newItem.getUpdatedAt())
		 .build();
		
		
	}
	private ItemEntity covertToEntity(ItemRequest request) {
		// TODO Auto-generated method stub
		return ItemEntity.builder()
		.itemId(UUID.randomUUID().toString())
		.description(request.getDescription())
		.price(request.getPrice())
		.build();
	}
	@Override
	public List<ItemResponce> fetchItems() {
		// TODO Auto-generated method stub
		return itemRepository.findAll()
		.stream()
		.map(itemEntity-> converToResponce(itemEntity))
		.collect(Collectors.toList());
	}

	@Override
	public void deleteItem(String itemId) {
		// TODO Auto-generated method stub
		
		// get the existing item 
	ItemEntity existingItem=itemRepository.findByitemId(itemId)
		.orElseThrow(()-> new RuntimeException("Item not found "+itemId));
	
	// to delete he file
	Boolean isFileDelerted= fileUploadService.deletrFile(existingItem.getItemId());
	if(isFileDelerted) {
		itemRepository.delete(existingItem);
		
	}else {
		throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Unable to delete the image");
		
	}
	 
	
		
		
	}

}
