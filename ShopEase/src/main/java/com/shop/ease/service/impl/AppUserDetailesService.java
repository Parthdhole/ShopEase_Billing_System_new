package com.shop.ease.service.impl;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.shop.ease.entity.UserEnitity;
import com.shop.ease.repository.UserRepository;

@Service
public class AppUserDetailesService implements  UserDetailsService{

	@Autowired
	private UserRepository userepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
			UserEnitity existinguser=userepository.findByEmail(email)
		.orElseThrow(()->new UsernameNotFoundException("Email not foun for the eamil"+email));
			return new User(existinguser.getEmail(),existinguser.getPassword(),Collections.singleton(new SimpleGrantedAuthority(existinguser.getRole())));
			
		
		
	}

}
