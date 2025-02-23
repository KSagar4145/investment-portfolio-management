//package com.investment.pojos;
//
//import com.investment.pojos.enums.TransactionType;
//import jakarta.persistence.AttributeOverride;
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.EnumType;
//import jakarta.persistence.Enumerated;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.ManyToOne;
//import jakarta.persistence.SequenceGenerator;
//import jakarta.persistence.Table;
//import jakarta.persistence.Temporal;
//import jakarta.persistence.TemporalType;
//import jakarta.validation.constraints.Min;
//import java.util.Date;
//
//@Entity
//@Table(name = "transactions")
//public class Transaction {
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transaction_seq")
//	@SequenceGenerator(name = "transaction_seq", sequenceName = "transaction_seq", allocationSize = 1)
//	@Column(name = "transaction_id")
//	private Long transactionId;
//
//
//	@ManyToOne
//	@JoinColumn(name = "trader_id", nullable = false)
//	private Trader trader;
//
//	@ManyToOne
//	@JoinColumn(name = "asset_id", nullable = false)
//	private Asset asset;
//
//	@Enumerated(EnumType.STRING)
//	@Column(name = "transaction_type", nullable = false)
//	private TransactionType transactionType;
//
//	@Column(name = "transaction_quantity", nullable = false)
//	@Min(value = 1L, message = "Quantity must be at least 1")
//	private Integer transactionQuantity;
//
//	@Column(name = "transaction_price", nullable = false)
//	@Min(value = 0L, message = "Price cannot be negative")
//	private Double transactionPrice;
//
//	@Column(name = "transaction_date", nullable = false)
//	@Temporal(TemporalType.TIMESTAMP)
//	private Date transactionDate;
//}
