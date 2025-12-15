package com.shop.ease.Controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authorization.AuthorityAuthorizationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.shop.ease.io.AuthRequest;
import com.shop.ease.io.AuthResponce;
import com.shop.ease.service.UserService;
import com.shop.ease.service.impl.AppUserDetailesService;
import com.shop.ease.util.JwtUtil;

import io.jsonwebtoken.Jwt;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AuthController {
	
	private final PasswordEncoder passwwordEncoder;
	
	private final AuthenticationManager authenticationManager;
	
	private final AppUserDetailesService appUserDetailesService;
	
	private final JwtUtil jwtUtil;
	
	private final UserService userService;
	
	
	
	@PostMapping("/login")
	public AuthResponce login(@RequestBody AuthRequest request) throws Exception {
		
		
		authenticate(request.getEamil(),request.getPassword());
		final UserDetails userDetails= appUserDetailesService.loadUserByUsername(request.getEamil());
		final String jwtToken= jwtUtil.generateToken(userDetails);
		String role =userService.getUserRole(request.getEamil());
//		UserService
//		// TODO:FETCH THE ROLE FORM REPOSITORY
		return new AuthResponce(request.getEamil(),role,jwtToken);
		
		
	}
	
	private void authenticate(String eamil, String password) throws Exception {
     try {
	   authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(eamil, password));
    } catch (DisabledException e) {
	// TODO: handle exception
    	throw new Exception("User disable");
     }catch(BadCredentialsException e) {
    	 
     throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"email OR Password is incorrect");
		
	}
	}
	@PostMapping("/encode")
	public String encoderPassword(@RequestBody Map<String,String> request) {
		
		return passwwordEncoder.encode(request.get("password"));
		
	}

}
