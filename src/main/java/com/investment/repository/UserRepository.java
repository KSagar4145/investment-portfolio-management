package com.investment.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.investment.pojos.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	User findByEmail(String email);

}
