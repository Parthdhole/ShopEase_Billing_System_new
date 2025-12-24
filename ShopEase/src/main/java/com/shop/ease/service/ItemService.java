package com.shop.ease.service;


import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.shop.ease.io.ItemRequest;
import com.shop.ease.io.ItemResponce;

public interface ItemService {
	
ItemResponce add(ItemRequest request,MultipartFile file );

 List<ItemResponce> fetchItems();
 
 void deleteItem(String itemId);
}
