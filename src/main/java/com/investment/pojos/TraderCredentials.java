package com.investment.pojos;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "trader_credentials")
public class TraderCredentials{
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "credentials_seq")
	@SequenceGenerator(name = "credentials_seq", sequenceName = "credentials_seq", allocationSize = 1)
	@Column(name = "credentials_id")
	private Long credentialsId;


	@Column(name = "trader_email", unique = true, nullable = false)
	@NotBlank(message = "Email cannot be blank")
	@Email(message = "Invalid email format")
	private String traderEmail;

	@Column(name = "trader_password", nullable = false)
	@NotBlank(message = "Password cannot be blank")
	@Size(min = 8, message = "Password must be at least 8 characters long")
	private String traderPassword;

	@OneToOne
	@JoinColumn(name = "trader_id", nullable = false)
	private Trader trader;
	
}
