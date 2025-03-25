package com.investment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.investment.pojos.VerificationCode;

public interface VerificationCodeRepository extends JpaRepository<VerificationCode, Long> {

	public VerificationCode findByUserId(Long userId);
}
