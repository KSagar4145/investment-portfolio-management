package com.investment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.investment.pojos.Trader;

public interface ITraderRepository extends JpaRepository<Trader, Long> {
	
}
