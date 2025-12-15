package com.shop.ease.config;



import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.shop.ease.service.impl.AppUserDetailesService;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

//    private final ShopEaseApplication shopEaseApplication;
//
//    private final AWSConfig AWSConfig;
//
//    SecurityConfig(AWSConfig AWSConfig, ShopEaseApplication shopEaseApplication) {
//        this.AWSConfig = AWSConfig;
//        this.shopEaseApplication = shopEaseApplication;
//    }
	
	
	 private final AppUserDetailesService appUserDetailesService;
	
	 @Bean
	    public CorsFilter corsFilter() {
	        return new CorsFilter(corsConfigurationSource());
	    }

	    private UrlBasedCorsConfigurationSource corsConfigurationSource() {

	        CorsConfiguration config = new CorsConfiguration();
	        config.setAllowedOrigins(List.of("http://localhost:5173"));
	        config.setAllowedMethods(
	                List.of("GET","POST","PUT","DELETE","PATCH","OPTIONS")
	        );
	        config.setAllowedHeaders(
	                List.of("Authorization","Content-Type")
	        );
	        config.setAllowCredentials(true);

	        UrlBasedCorsConfigurationSource source =
	                new UrlBasedCorsConfigurationSource();
	        source.registerCorsConfiguration("/**", config);

	        return source;
	    }
	    
	    
	    
	    /**his SecurityConfig class enables Spring Security and defines a global CORS filter using @Configuration and @EnableWebSecurity.

CorsConfiguration allows requests from http://localhost:5173 with specific HTTP methods, headers, and credentials, and applies them to all endpoints (/**).

The error occurred because wrong imports (Tomcat/Reactive) were used instead of Spring MVC’s CorsFilter and UrlBasedCorsConfigurationSource.
**/
	
	
	    
	     @Bean
	     public SecurityFilterChain securityfilterchain(HttpSecurity http) throws Exception{
	    	 
	    	 http.cors(Customizer.withDefaults())
	    	 .csrf(AbstractHttpConfigurer::disable)
	    	 .authorizeHttpRequests(auth->auth.requestMatchers("/login").permitAll()
	    			 .requestMatchers("/category","/items").hasAnyRole("USER","ADMIN")
	    			 .requestMatchers("/admin/**").hasRole("Admin")
	    			 .anyRequest().authenticated())
	    			 .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
	    	 return http.build();
	    			 
	    	 
	    
	    	 
	     }
	     
	     @Bean
	     public PasswordEncoder passwordencoder() {
	    	 return new BCryptPasswordEncoder();
	    	
	    	 
	     }
	     public AuthenticationManager authenticationManager() {
	    	 DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
	    	 authProvider.setUserDetailsService(appUserDetailesService);
	    	 authProvider.setPasswordEncoder(passwordencoder());
	    	 return new ProviderManager(authProvider);
	    	 
	    	 
	     }
	     
	     
	     
	
}

