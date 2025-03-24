package com.investment.pojos;

import com.investment.pojos.enums.VerificationType;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class TwoFactorAuth {
	
	private boolean isEnabled= false;
	private VerificationType sendTo;

}
