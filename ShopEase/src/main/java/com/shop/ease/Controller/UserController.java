package com.shop.ease.Controller;



import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.shop.ease.io.UserRequest;
import com.shop.ease.io.UserResponce;
import com.shop.ease.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class UserController{

	
	private final UserService userService;
	@PostMapping("/register")
	@ResponseStatus(HttpStatus.CREATED)
	public UserResponce registerUser(@RequestBody UserRequest request ){
		try {
			
			return userService.createUser(request);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Unable to create user"+e.getMessage());
			
		}
		
	   
	}
	@GetMapping("/users")
	public List<UserResponce> redUser(){
		return userService.readUsers();
		   
	   }
	
	@DeleteMapping("/users/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)

	public void deleteUser(@PathVariable String id) {
		try {
			userService.delete(id);
		}catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"USER NOT FOUND");
		}
		
	}
}
