package com.investment.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.investment.pojos.TraderCredentials;

public interface ITraderCredentialsRepository  extends JpaRepository<TraderCredentials, Long>{
	
	
	Optional<TraderCredentials> findByTraderEmailAndTraderPassword(String traderEmail, String traderPassword);
	
	

}
