//package com.investment.pojos;
//
//import com.investment.pojos.enums.AssetType;
//import jakarta.persistence.AttributeOverride;
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.EnumType;
//import jakarta.persistence.Enumerated;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.SequenceGenerator;
//import jakarta.persistence.Table;
//import jakarta.validation.constraints.Min;
//import jakarta.validation.constraints.NotBlank;
//import jakarta.validation.constraints.Size;
//
//@Entity
//@Table(name = "assets")
//public class Asset {
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "asset_seq")
//	@SequenceGenerator(name = "asset_seq", sequenceName = "asset_seq", allocationSize = 1)
//	@Column(name = "asset_id")
//	private Long assetId;
//
//
//	@NotBlank(message = "Asset name cannot be blank")
//	@Size(max = 100, message = "Asset name cannot exceed 100 characters")
//	@Column(name = "asset_name", nullable = false, length = 100)
//	private String assetName;
//
//	@Enumerated(EnumType.STRING)
//	@Column(name = "asset_type", nullable = false)
//	private AssetType assetType;
//
//	@NotBlank(message = "Symbol cannot be blank")
//	@Size(max = 10, message = "Symbol cannot exceed 10 characters")
//	@Column(name = "asset_symbol", unique = true, nullable = false, length = 10)
//	private String assetSymbol;
//
//	@Column(name = "current_price", nullable = false)
//	@Min(value = 0L, message = "Price cannot be negative")
//	private Double currentPrice;
//}
//
