package com.investment.pojos;

import com.investment.pojos.enums.TraderRole;
import com.investment.pojos.enums.TraderStatus;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "traders")
public class Trader{

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "trader_seq")
	@SequenceGenerator(name = "trader_seq", sequenceName = "trader_seq", allocationSize = 1)
	@Column(name = "trader_id")
	private Long traderId;

	@Column(name = "trader_name")
	@NotBlank(message = "Name cannot be blank")
	@Size(max = 100, message = "Name cannot exceed 100 characters")
	private String traderName;

	@Column(name = "trader_phone_nbr")
	@NotBlank(message = "Phone number cannot be blank")
	@Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be exactly 10 digits")
	private String traderPhoneNumber;

	@Column(name = "trader_address")
	@NotBlank(message = "Address cannot be blank")
	@Size(max = 255, message = "Address cannot exceed 255 characters")
	private String traderAddress;

	@Column(name = "trader_dob")
	@Temporal(TemporalType.DATE)
	private Date traderDateOfBirth;

	@Column(name = "trader_created_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date traderCreatedAt;

	@Column(name = "trader_updated_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date traderUpdatedAt;

	@Column(name = "trader_balance", nullable = false)
	@Min(value = 0L, message = "Balance cannot be negative")
	private Double traderBalance;

	@Column(name = "trader_status")
	@Enumerated(EnumType.STRING)
	private TraderStatus traderStatus;

	@Column(name = "trader_role")
	@Enumerated(EnumType.STRING)
	private TraderRole traderRole;

	@Column(name = "kyc_verified", nullable = false)
	private Boolean kycVerified;

	@OneToOne(mappedBy = "trader", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
	private TraderCredentials credentials;
	
    @OneToOne(mappedBy = "trader", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private PortfolioSummary portfolioSummary;
	
}
