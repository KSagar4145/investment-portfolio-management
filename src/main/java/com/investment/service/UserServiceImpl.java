package com.investment.service;

import com.investment.config.securityconfig.jwt.JwtUtil;
import com.investment.pojos.TwoFactorAuth;
import com.investment.pojos.User;
import com.investment.pojos.enums.VerificationType;
import com.investment.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public User findUserProfileByJwt(String jwt) throws Exception {
    	
    	
        String email = jwtUtil.extractUsername(jwt);
        User user = userRepository.findByEmail(email);
        if(user==null) {
        	throw new Exception("user not found");
    	}
        return user;
    }

    @Override
    public User findUserByEmail(String email) throws Exception {
    	 User user = userRepository.findByEmail(email);
         if(user==null) {
         	throw new Exception("user not found");
     	}
         return user;
    }

    @Override
    public User findUserById(Long userId) throws Exception {
        return userRepository.findById(userId)
            .orElseThrow(() -> new Exception("User not found with id: " + userId));
    }


    @Override
    public User enableTwoFactorAuthentication(VerificationType verificationType, String sendTo,User user) {
        TwoFactorAuth twoFactorAuth = new TwoFactorAuth();
        twoFactorAuth.setEnabled(true);
        twoFactorAuth.setSendTo(verificationType);
    	user.setTwoFactorAuth(twoFactorAuth);
        return userRepository.save(user);
    }

    @Override
    public User updatePassword(User user, String newPassword) {
        user.setPassword(passwordEncoder.encode(newPassword));
        return userRepository.save(user);
    }
}
