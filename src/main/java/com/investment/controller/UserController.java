package com.investment.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.investment.pojos.ForgotPasswordToken;
import com.investment.pojos.ForgotPasswordTokenRequest;
import com.investment.pojos.User;
import com.investment.pojos.VerificationCode;
import com.investment.pojos.enums.VerificationType;
import com.investment.service.EmailService;
import com.investment.service.ForgotPasswordService;
import com.investment.service.UserService;
import com.investment.service.VerificationCodeService;
import com.investment.utils.OtpUtils;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private EmailService emailService;

	@Autowired
	private VerificationCodeService verificationCodeService;

	
	@Autowired
	private ForgotPasswordService forgotPasswordService;

	@GetMapping("/api/users/profile")
	public ResponseEntity<?> getUserProfile(@RequestHeader("Authorization") String jwt) throws Exception{
		User user = userService.findUserProfileByJwt(jwt);

		return ResponseEntity.status(HttpStatus.OK).body(user);
	}


	@PostMapping("/api/users/verification/{verificationType}/send-otp")
	public ResponseEntity<?> sendVerificationOtp(
			@RequestHeader("Authorization") String jwt,
			@PathVariable VerificationType verificationType
			) throws Exception{
		User user = userService.findUserProfileByJwt(jwt);
		VerificationCode verificationCode = verificationCodeService.getVerificationCodeByUser(user.getId());

		if(verificationCode == null) {
			verificationCodeService.sendVerificationCode(user, verificationType);
		}


		if(verificationType.equals(VerificationType.EMAIL)) {
			emailService.sendOtpVerificationEmail(user.getEmail(),
					verificationCode.getOtp());
		}


		return ResponseEntity.status(HttpStatus.OK).body("Verification OTP send successfully");
	}



	@PatchMapping("/api/users/enable-two-factor/verify-otp/{otp}")
	public ResponseEntity<?> enableTwoFactorAuthentication(
			String jwt, String otp) throws Exception {
		User user = userService.findUserProfileByJwt(jwt);

		VerificationCode verificationCode = verificationCodeService.getVerificationCodeByUser(user.getId());

		String sendTo = verificationCode.getVerificationType().equals(VerificationType.EMAIL) 
				? verificationCode.getEmail() 
						: verificationCode.getMobile();

		boolean isVerified = verificationCode.getOtp().equals(otp);

		if (isVerified) {
			User updatedUser = userService.enableTwoFactorAuthentication(
					verificationCode.getVerificationType(), sendTo, user);

			verificationCodeService.deleteVerificationCodeById(verificationCode);

			return new ResponseEntity<>(updatedUser, HttpStatus.OK);
		}

		throw new Exception("wrong otp");
	}
	
	
	
	@PostMapping("/api/users/reset-password/send-otp")
	public ResponseEntity<?> sendForgotPasswordOTP(
			@RequestBody ForgotPasswordTokenRequest req
			) throws Exception{
		
		
		User user = userService.findUserByEmail(req.getSendTo());
		String otp = OtpUtils.generateOTP();
		UUID uuid = UUID.randomUUID();
		String id = uuid.toString();

		ForgotPasswordToken token = forgotPasswordService.findByUser(user.getId());
		
		if(token == null) {
			token = forgotPasswordService.createToken(user, id, otp, req.getVerificationType(), req.getSendTo());
		}
		
		
		if(req.getVerificationType().equals(VerificationType.EMAIL)) {
			emailService.sendOtpVerificationEmail(id, otp);
		}
		
		
	
		return ResponseEntity.status(HttpStatus.OK).body("Forgot Password OTP send successfully");
	}
	
	
	





}
