package com.investment.pojos;

import com.investment.pojos.enums.VerificationType;

import lombok.Data;

@Data
public class ForgotPasswordTokenRequest {
	
	private String sendTo;
	private VerificationType verificationType;

}
