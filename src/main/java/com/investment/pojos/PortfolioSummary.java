//package com.investment.pojos;
//
//import jakarta.persistence.*;
//import jakarta.persistence.Entity;
//import jakarta.persistence.Id;
//import jakarta.persistence.OneToOne;
//import jakarta.persistence.Table;
//
//@Entity
//@Table(name = "portfolio_summary")
//public class PortfolioSummary {
//	
//
//    @Id
//    @Column(name = "trader_id")
//    private Long traderId;  // Foreign Key to Trader
//
//    @OneToOne
//    @MapsId
//    @JoinColumn(name = "trader_id")
//    private Trader trader;  // Relationship with Trader
//  
//  @Column(name = "total_holdings")
//  private Integer totalHoldings;
//  
//  @Column(name = "total_investment")
//  private Double totalInvestment;
//  
//  @Column(name = "total_profit_loss")
//  private Double totalProfitLoss;
//}
