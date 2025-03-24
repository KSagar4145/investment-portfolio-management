package com.investment.pojos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.investment.pojos.enums.UserRole;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String fullName;
	
	
	@NotBlank(message = "Email cannot be blank")
	@Email(message = "Invalid email format")
	@Column(unique = true) 
	private String email;

	@NotBlank(message = "Password cannot be blank")
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String password;
	
	
	@Embedded
	private TwoFactorAuth twoFactorAuth= new TwoFactorAuth();
	
	@Enumerated(EnumType.STRING)
	private UserRole role = UserRole.ROLE_CUSTOMER;


	

}
