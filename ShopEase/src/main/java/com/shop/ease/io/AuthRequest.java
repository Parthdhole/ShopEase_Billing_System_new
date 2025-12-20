package com.shop.ease.io;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

// Keep existing getter, add @Data for setters and @NoArgsConstructor for Jackson
@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthRequest {
	private String email;
	private String password;
}

