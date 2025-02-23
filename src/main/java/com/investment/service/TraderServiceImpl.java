package com.investment.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.investment.pojos.PortfolioSummary;
import com.investment.pojos.Trader;
import com.investment.pojos.TraderCredentials;
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
	
	
	
	
	 public Trader authenticateTrader(String email, String password) {
	        Optional<TraderCredentials> credentials = traderCredentialsRepository.findByTraderEmailAndTraderPassword(email, password);
	        return credentials.map(TraderCredentials::getTrader).orElse(null);
	    }

	    public PortfolioSummary getPortfolio(Long traderId) {
	        return portfolioSummaryRepository.findById(traderId).orElse(null);
	    }

	    public String buyStock(Long traderId, Double amount) {
	        Trader trader = traderRepository.findById(traderId).orElse(null);
	        if (trader == null) return "Trader not found";

	        if (trader.getTraderBalance() < amount) {
	            return "Insufficient balance!";
	        }

	        trader.setTraderBalance(trader.getTraderBalance() - amount);
	        traderRepository.save(trader);

	        PortfolioSummary portfolio = trader.getPortfolioSummary();
	        portfolio.setTotalInvestment(portfolio.getTotalInvestment() + amount);
	        portfolioSummaryRepository.save(portfolio);

	        return "Stock bought successfully!";
	    }

	    public String sellStock(Long traderId, Double amount) {
	        Trader trader = traderRepository.findById(traderId).orElse(null);
	        if (trader == null) return "Trader not found";

	        trader.setTraderBalance(trader.getTraderBalance() + amount);
	        traderRepository.save(trader);

	        PortfolioSummary portfolio = trader.getPortfolioSummary();
	        portfolio.setTotalInvestment(portfolio.getTotalInvestment() - amount);
	        portfolioSummaryRepository.save(portfolio);

	        return "Stock sold successfully!";
	    }


}
