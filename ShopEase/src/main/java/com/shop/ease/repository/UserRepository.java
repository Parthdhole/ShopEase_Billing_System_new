package com.shop.ease.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shop.ease.entity.UserEnitity;

import java.lang.StackWalker.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEnitity, Long>  {
	Optional<UserEnitity> findByEmail(String email);
	
	Optional<UserEnitity> findByuserId(String userId);
	
  
}
