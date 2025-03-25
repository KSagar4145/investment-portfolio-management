package com.investment.service;

import com.investment.pojos.User;
import com.investment.pojos.VerificationCode;
import com.investment.pojos.enums.VerificationType;

public interface VerificationCodeService {
	
	VerificationCode sendVerificationCode(User user, VerificationType verificationType);

    VerificationCode getVerificationCodeById(Long id) throws Exception;

    VerificationCode getVerificationCodeByUser(Long userId);

    void deleteVerificationCodeById(VerificationCode verificationCode);

}
