package com.shop.ease.Controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authorization.AuthorityAuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.shop.ease.io.AuthRequest;
import com.shop.ease.io.AuthResponce;
import com.shop.ease.repository.UserRepository;
import com.shop.ease.service.UserService;
import com.shop.ease.service.impl.AppUserDetailesService;
import com.shop.ease.util.JwtUtil;

import io.jsonwebtoken.Jwt;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor

// THIS REPSONSIBLE FOR GENERATING THE PASSWORD
public class AuthController {
	
	private final PasswordEncoder passwwordEncoder;
	
	private final AuthenticationManager authenticationManager;
	
	private final AppUserDetailesService appUserDetailesService;
	
	private final JwtUtil jwtUtil;
	
	private final UserService userService;
	
	
	
	
//	@PostMapping("/login")
//	
//	public AuthResponce login(@RequestBody AuthRequest request)  {
//	    System.out.println("➡️ LOGIN REQUEST RECEIVED: " + request.getEmail());
//		
//		try {
//			authenticate(request.getEmail(),request.getPassword());
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	    System.out.println("✅ AUTHENTICATION SUCCESS for: " + request.getEmail());
//	    // 2️⃣ Load user details
//		final UserDetails userDetails= appUserDetailesService.loadUserByUsername(request.getEmail());
//	    // 4️⃣ Generate JWT
//		final String jwtToken= jwtUtil.generateToken(userDetails);
//		 System.out.println("🔐 JWT GENERATED"+ jwtToken);
//		// 5️⃣ Fetch role
//		String role =userService.getUserRole(request.getEmail());
//		
////		// TODO:FETCH THE ROLE FORM REPOSITORY
//		
//		  System.out.println("🔑 ROLE: " + role);
//		 
//		return new AuthResponce(request.getEmail(),
//	            jwtToken,
//	            role);
//		
//	}
	@PostMapping("/login")
	public AuthResponce login(@RequestBody AuthRequest request) throws Exception {
		System.out.println("➡️ LOGIN REQUEST RECEIVED: " + request.getEmail());
	    authenticate(request.getEmail(), request.getPassword());
	    
	    
    System.out.println("✅ AUTHENTICATION SUCCESS for: " + request.getEmail());

	    final UserDetails userDetails = appUserDetailesService.loadUserByUsername(request.getEmail());
	    
	    final String jwtToken = jwtUtil.generateToken(userDetails); 
		 System.out.println("🔐 JWT GENERATED"+ jwtToken);
	    String role = userService.getUserRole(request.getEmail());
 	  System.out.println("🔑 ROLE: " + role);
	    return new AuthResponce(request.getEmail(), jwtToken, role);
	    
	}
	
	private void authenticate(String email, String password) throws Exception {
     System.out.println("➡️ [DEBUG] Inside authenticate method for: " + email);
     try {
       System.out.println("➡️ [DEBUG] Delegating to authenticationManager");
	   authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
       System.out.println("✅ [DEBUG] authenticationManager returned success");
    } catch (DisabledException e) {
    	System.out.println("❌ [DEBUG] User disabled: " + e.getMessage());
    	throw new Exception("User disable");
     }catch(BadCredentialsException e) {
    	 System.out.println("❌ [DEBUG] Bad credentials for: " + email);
         throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"email OR Password is incorrect");
		
	} catch (Exception e) {
		System.out.println("❌ [DEBUG] Authentication failed: " + e.getClass().getName() + " " + e.getMessage());
		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Authentication failed: " + e.getMessage());
	}
	}
	@PostMapping("/encode")
	public String encoderPassword(@RequestBody Map<String,String> request) {
		
		return passwwordEncoder.encode(request.get("password"));
		
	}

}
