package com.investment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.investment.pojos.TwoFactorOTP;

public interface TwoFactorOtpRepository extends JpaRepository<TwoFactorOTP, String>{

	
	TwoFactorOTP findByUserId(Long userId);
}
