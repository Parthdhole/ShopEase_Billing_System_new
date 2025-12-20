package com.shop.ease.Filters;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.shop.ease.service.impl.AppUserDetailesService;
import com.shop.ease.util.JwtUtil;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter{

	
	private final AppUserDetailesService UserDetailedService;
	private final JwtUtil jwtUtil;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
	// Bypass JWT auth for login/encode to avoid interference
//	final String path = request.getServletPath();
//	if ("/login".equals(path) || "/encode".equals(path)) {
//		filterChain.doFilter(request, response);
//		return;
//	}
		
//		 String path = request.getRequestURI();  // safer than getServletPath
//		    if (path.equals("/login")) {
//		        filterChain.doFilter(request, response);
//		        return;
//		    }
//		
		
	final String authorizationHeader= request.getHeader("Authorization");
	System.out.println("🔹 Incoming request URI: " + request.getRequestURI());
    System.out.println("🔹 Authorization header: " + authorizationHeader);
	
	String email=null;
	String jwt = null;
	

    // 1️⃣ If token exists, extract email
	if(authorizationHeader!=null && authorizationHeader.startsWith("Bearer ")) {
		
		jwt=authorizationHeader.substring(7);
		try {
            email = jwtUtil.extractUsername(jwt);
            System.out.println("🔹 Extracted username from JWT: " + email);
        } catch (Exception e) {
            System.out.println("⚠️ Invalid JWT token: " + e.getMessage());
        }
//		email=jwtUtil.extractUsername(jwt);
		
			
	}
	else {
		System.out.println(" No Bearer token found in request");
	}
//	System.out.println("🔹 JWT Filter path: " + request.getServletPath());
//	String path = request.getRequestURI();
//	System.out.println("🔹 JWT Filter path: " + path);
//	System.out.println("🔹 JWT Token: " + jwt);
	
	if(email!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
		
	UserDetails userDetail=	UserDetailedService.loadUserByUsername(email);
	if(jwtUtil.validateToken(jwt, userDetail)) {
		UsernamePasswordAuthenticationToken authenticationToken= 
				new UsernamePasswordAuthenticationToken( userDetail, null,userDetail.getAuthorities());
		authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
		SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        System.out.println("✅ SecurityContext updated with user: " + email);
	}
	else {
        System.out.println("❌ JWT validation failed for user: " + email);
    }
		
	}
	filterChain.doFilter(request, response);
	
	
	
	}
	

}

