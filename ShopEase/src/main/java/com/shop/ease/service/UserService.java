package com.shop.ease.service;



import java.util.List;

import com.shop.ease.io.UserRequest;
import com.shop.ease.io.UserResponce;


public interface UserService {
	
	UserResponce createUser(UserRequest request);
	
	String getUserRole(String email);
	List<UserResponce> readUsers();
	
	void delete(String id);
	
	
	

}
