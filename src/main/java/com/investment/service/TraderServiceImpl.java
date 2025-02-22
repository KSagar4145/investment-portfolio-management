package com.investment.service;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.investment.pojos.Trader;
import com.investment.repository.ITraderRepository;

@Service
@Transactional
public class TraderServiceImpl implements ITraderService {
	
	@Autowired
	private ITraderRepository traderRepository;

	@Override
	public Trader createAndSaveTrader(Trader trader) {
		
		System.out.println("TraderServiceImpl createAndSaveTrader");
		return traderRepository.save(trader);
	}

}
