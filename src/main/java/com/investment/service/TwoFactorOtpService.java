package com.investment.service;

import com.investment.pojos.TwoFactorOTP;
import com.investment.pojos.User;

public interface TwoFactorOtpService {
	
	TwoFactorOTP createTwoFactorOtp(User user, String otp, String jwt);
	
	TwoFactorOTP findByUser(Long userId);
	
	
	TwoFactorOTP findById(String id);
	
	boolean verifyTwoFactorOtp(TwoFactorOTP twofactorOpt, String otp);
	
	
	void deleteTwoFactorOtp(TwoFactorOTP twofactorOpt);
	

}
