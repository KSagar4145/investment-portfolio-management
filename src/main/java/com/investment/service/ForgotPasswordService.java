package com.investment.service;

import com.investment.pojos.ForgotPasswordToken;
import com.investment.pojos.User;
import com.investment.pojos.enums.VerificationType;

public interface ForgotPasswordService {

    ForgotPasswordToken createToken(User user, 
                                    String id, 
                                    String otp, 
                                    VerificationType verificationType, 
                                    String sendTo);

    ForgotPasswordToken findById(String id);

    ForgotPasswordToken findByUser(Long userId);

    void deleteToken(ForgotPasswordToken token);
}

