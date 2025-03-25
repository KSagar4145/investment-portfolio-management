package com.investment.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.investment.config.securityconfig.jwt.JwtUtil;
import com.investment.pojos.AuthResponse;
import com.investment.pojos.TwoFactorOTP;
import com.investment.pojos.User;
import com.investment.repository.UserRepository;
import com.investment.service.EmailService;
import com.investment.service.TwoFactorOtpService;
import com.investment.utils.OtpUtils;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private TwoFactorOtpService twoFactorOtpService;

	@Autowired
	private JwtUtil jwtUtil;
	
	
	@Autowired
    private EmailService emailService;

	@PostMapping("/signup")
	public ResponseEntity<AuthResponse> register(@RequestBody User user) {
		// Check if email already exists
		User existingUser = userRepository.findByEmail(user.getEmail());
		if (existingUser != null)  {
			AuthResponse authResponse = new AuthResponse();
			authResponse.setStatus(false);
			authResponse.setMessage("Email already exists for another account.");
			return new ResponseEntity<>(authResponse, HttpStatus.CONFLICT); // 409 Conflict
		}

		// Register new user
		User newUser = new User();
		newUser.setFullName(user.getFullName());
		newUser.setEmail(user.getEmail());
		newUser.setPassword(passwordEncoder.encode(user.getPassword()));  // Encode password
		User savedUser = userRepository.save(newUser);

		// Generate JWT token
		String token = jwtUtil.generateToken(user.getEmail());

		AuthResponse authResponse = new AuthResponse();
		authResponse.setJwt(token);
		authResponse.setStatus(true);
		authResponse.setMessage("Registration Successful.");

		return new ResponseEntity<>(authResponse, HttpStatus.CREATED);
	}


	@PostMapping("/signin")
	public ResponseEntity<?> login(@RequestBody User user) {
		// Check if the user exists with the provided email
		User existingUser = userRepository.findByEmail(user.getEmail());

		if (ObjectUtils.isEmpty(existingUser)) {
			return new ResponseEntity<>("User not found with this email.", HttpStatus.NOT_FOUND);
		}
		User foundUser = existingUser;
		if (!passwordEncoder.matches(user.getPassword(), foundUser.getPassword())) {
			return new ResponseEntity<>("Invalid password.", HttpStatus.UNAUTHORIZED);
		}

		String token = jwtUtil.generateToken(foundUser.getEmail());
		User authUser = existingUser;
		if (user.getTwoFactorAuth().isEnabled()) {
			AuthResponse authResponse = new AuthResponse();
			//authResponse.setJwt(token);
			authResponse.setStatus(true);
			authResponse.setMessage("Two factor auth is enabled.");
			String otp = OtpUtils.generateOTP();

			TwoFactorOTP oldTwoFactorOtp = twoFactorOtpService.findByUser(authUser.getId());
			if(oldTwoFactorOtp!=null) {
				twoFactorOtpService.deleteTwoFactorOtp(oldTwoFactorOtp);
			}

			TwoFactorOTP newTwoFactorOtp = twoFactorOtpService
					.createTwoFactorOtp(authUser, otp, token);
			
			emailService.sendOtpVerificationEmail(token, otp);
			
			authResponse.setSession(newTwoFactorOtp.getId());
			return new ResponseEntity<>(authResponse,HttpStatus.ACCEPTED);
		}
		
		AuthResponse authResponse = new AuthResponse();
		authResponse.setJwt(token);
		authResponse.setStatus(true);
		authResponse.setMessage("Login Successful.");
		authResponse.setTwoFactorAuthEnabled(existingUser.getTwoFactorAuth().isEnabled());
		return new ResponseEntity<>(authResponse, HttpStatus.OK);
	}
	
	
	


    @PostMapping("/two-factor/otp/{otp}")
public ResponseEntity<?> verifySignInOtp(@PathVariable String otp, @RequestParam String id) {
        // Fetch the TwoFactorOTP object by ID
        TwoFactorOTP twoFactorOTP = twoFactorOtpService.findById(id);
        
        // Check if the OTP is valid
        if (twoFactorOTP == null) {
            // If the OTP object is not found, return a "not found" response
            return new ResponseEntity<>("OTP record not found.", HttpStatus.NOT_FOUND);
        }
        
        // If OTP is verified successfully
        if (twoFactorOtpService.verifyTwoFactorOtp(twoFactorOTP, otp)) {
        	AuthResponse authResponse = new AuthResponse();
            authResponse.setJwt(twoFactorOTP.getJwt());
            authResponse.setMessage("Two Factor Authentication Successful.");
            authResponse.setTwoFactorAuthEnabled(true);
            return new ResponseEntity<>(authResponse, HttpStatus.OK);
        }

        // If OTP verification fails
        return new ResponseEntity<>("Invalid OTP.", HttpStatus.BAD_REQUEST);
    }


}

