package com.investment.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.investment.pojos.PortfolioSummary;
import com.investment.pojos.Trader;
import com.investment.repository.IPortfolioSummaryRepository;
import com.investment.repository.ITraderCredentialsRepository;
import com.investment.repository.ITraderRepository;

@Service
@Transactional
public class TraderServiceImpl implements ITraderService {

	@Autowired
	private ITraderRepository traderRepository;

	@Autowired
	private ITraderCredentialsRepository traderCredentialsRepository;

	@Autowired
	private IPortfolioSummaryRepository portfolioSummaryRepository;


	public Trader addTrader(Trader trader) {
		Trader savedTrader = traderRepository.save(trader);
		PortfolioSummary portfolioSummary = new PortfolioSummary();
		portfolioSummary.setTrader(savedTrader);
		portfolioSummary.setTotalHoldings(0);  
		portfolioSummary.setTotalInvestment(0.00);  
		portfolioSummary.setTotalProfitLoss(0.00);
		portfolioSummaryRepository.save(portfolioSummary);
		savedTrader.setPortfolioSummary(portfolioSummary);
		return traderRepository.save(savedTrader);
	}


}
