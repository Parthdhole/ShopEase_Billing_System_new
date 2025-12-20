package com.shop.ease.service.impl;


import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.shop.ease.entity.UserEnitity;
import com.shop.ease.io.UserRequest;
import com.shop.ease.io.UserResponce;
import com.shop.ease.repository.UserRepository;
import com.shop.ease.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

	
	
	private final  UserRepository useRepository;
	private final PasswordEncoder passwordEncoder;
	@Override
	public UserResponce createUser(UserRequest request) {

       UserEnitity newUser= convertToEntity(request);
       newUser=useRepository.save(newUser);
       return convertToRespnonse(newUser);
       
       
	}

	private UserResponce convertToRespnonse(UserEnitity newUser) {
		// TODO Auto-generated method stub
		return UserResponce.builder()
		.name(newUser.getName())
		.email(newUser.getEmail())
	     .userId(newUser.getUserid())
	     .createdAt(newUser.getCreatedAt())
	     .role(newUser.getRole())
	     .build();
	}

	private UserEnitity convertToEntity(UserRequest request) {
		// TODO Auto-generated method stub
		return UserEnitity.builder()
		.userid(UUID.randomUUID().toString())
		.email(request.getEmail())
		.password(passwordEncoder.encode(request.getPassword()))
		.role("ROLE_" + request.getRole().toUpperCase())
		.build();
	}

	@Override
	public String getUserRole(String email) {
		// TODO Auto-generated method stub
		
	UserEnitity existingUser=useRepository.findByEmail(email)
		.orElseThrow(()-> new UsernameNotFoundException("User not found for the email"));
	return existingUser.getRole();
	
	}

	@Override
	public List<UserResponce> readUsers() {
		// TODO Auto-generated method stub
	return 	useRepository.findAll()
		.stream()
		.map(user->convertToRespnonse(user))
		.collect(Collectors.toList());
		
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		 UserEnitity existingUser =	useRepository.findByUserid(id)
		.orElseThrow(()->new UsernameNotFoundException("User not found "));
		 
		 useRepository.delete(existingUser);
		 
		
		
		
	}

}
