package com.investment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.investment.pojos.PortfolioSummary;

public interface IPortfolioSummaryRepository extends JpaRepository<PortfolioSummary, Long> {

}
