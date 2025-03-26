package com.investment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.investment.pojos.ForgotPasswordToken;
import com.investment.pojos.TwoFactorOTP;

public interface ForgotPasswordRepository extends JpaRepository<ForgotPasswordToken, String>{

	ForgotPasswordToken findByUserId(Long userId);
}
