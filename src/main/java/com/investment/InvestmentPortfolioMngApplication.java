package com.investment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InvestmentPortfolioMngApplication {

	public static void main(String[] args) {
		SpringApplication.run(InvestmentPortfolioMngApplication.class, args);
		
		System.err.println("InvestmentPortfolioMngApplication up and running..........");
	}

}
