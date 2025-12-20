package com.shop.ease.entity;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="tbl_users")
@Builder
@Data
public class UserEnitity {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(unique = true)
	private String userid;
	private  String email;
	private  String password;
	private String role;
	private String name;
	@CreationTimestamp
	@Column(updatable=false)
	private Timestamp createdAt;
	@UpdateTimestamp
	private Timestamp  updatedAt;
	
	
	
	
	

}
