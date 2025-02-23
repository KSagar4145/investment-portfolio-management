package com.investment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.investment.pojos.TraderCredentials;

public interface ITraderCredentialsRepository  extends JpaRepository<TraderCredentials, Long>{
	

}
