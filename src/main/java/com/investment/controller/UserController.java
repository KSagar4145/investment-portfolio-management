package com.investment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.investment.pojos.User;
import com.investment.pojos.enums.VerificationType;
import com.investment.service.EmailService;
import com.investment.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private EmailService emailService;
	
	
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
		return ResponseEntity.status(HttpStatus.OK).body(user);
	}
	
	
	
	@PatchMapping("/api/users/enable-two-factor/verify-otp/{otp}")
	public ResponseEntity<?> enableTwoFactorAuthentication(@RequestHeader("Authorization") String jwt) throws Exception{
		User user = userService.findUserProfileByJwt(jwt);
		return ResponseEntity.status(HttpStatus.OK).body(user);
	}
	
	
	

}
