package com.investment.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.investment.pojos.TwoFactorOTP;
import com.investment.pojos.User;
import com.investment.repository.TwoFactorOtpRepository;
@Service
public class TwoFactorOtpServiceImpl implements TwoFactorOtpService {

	@Autowired
	private TwoFactorOtpRepository twoFactorOtpRepo;
	
	@Override
	public TwoFactorOTP createTwoFactorOtp(User user,String otp, String jwt) {
		
		UUID uuid = UUID.randomUUID();
		String id = uuid.toString();
		
		TwoFactorOTP twoFactorOtp = new TwoFactorOTP();
		twoFactorOtp.setOtp(otp);
		twoFactorOtp.setJwt(jwt);
		twoFactorOtp.setId(id);
		twoFactorOtp.setUser(user);
		twoFactorOtp.setOtp(otp);
		
		return twoFactorOtpRepo.save(twoFactorOtp);
	}

	@Override
	public TwoFactorOTP findByUser(Long userId) {
		return twoFactorOtpRepo.findByUserId(userId);
	}

	@Override
	public TwoFactorOTP findById(String id) {
		Optional<TwoFactorOTP> otp = twoFactorOtpRepo.findById(id);
		return otp.orElse(null);
	}

	@Override
	public boolean verifyTwoFactorOtp(TwoFactorOTP twofactorOpt, String otp) {
		return twofactorOpt.getOtp().equals(otp);
	}

	@Override
	public void deleteTwoFactorOtp(TwoFactorOTP twofactorOpt) {
		twoFactorOtpRepo.delete(twofactorOpt);
	}

}
