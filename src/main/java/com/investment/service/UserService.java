package com.investment.service;

import com.investment.pojos.User;
import com.investment.pojos.enums.VerificationType;

public interface UserService {
    
     User findUserProfileByJwt(String jwt) throws Exception;
    
     User findUserByEmail(String email) throws Exception;
    
     User findUserById(Long userId) throws Exception;
 	
     User enableTwoFactorAuthentication(VerificationType verificationType, String sendTo, User user);
    
     User updatePassword(User user, String newPassword);

}

