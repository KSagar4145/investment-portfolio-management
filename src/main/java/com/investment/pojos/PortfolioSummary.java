package com.investment.pojos;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "portfolio_summary")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PortfolioSummary {


	@Id
	@Column(name = "trader_id")
	private Long traderId;  

	@OneToOne(mappedBy = "portfolioSummary", cascade = CascadeType.ALL)
	@MapsId
	@JsonIgnore
	private Trader trader;  

	@Column(name = "total_holdings")
	private Integer totalHoldings;

	@Column(name = "total_investment")
	private Double totalInvestment;

	@Column(name = "total_profit_loss")
	private Double totalProfitLoss;
}
