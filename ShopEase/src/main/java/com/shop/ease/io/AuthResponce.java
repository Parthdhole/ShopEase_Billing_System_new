package com.shop.ease.io;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponce {


	private String eamil;
	private String token;
	private String role;
	
}
