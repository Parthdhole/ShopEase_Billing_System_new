package com.shop.ease.io;

import java.security.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data

public class UserResponce {
	
	private String userId;
	private String name;
	private String email;
	private String password;
	private Timestamp createAt;
	private Timestamp updatedAt;
	private String role;
	
	
	

}
