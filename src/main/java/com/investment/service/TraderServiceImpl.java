package com.investment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.investment.pojos.Trader;
import com.investment.pojos.TraderCredentials;
import com.investment.repository.ITraderCredentialsRepository;
import com.investment.repository.ITraderRepository;

@Service
@Transactional
public class TraderServiceImpl implements ITraderService {
	
	@Autowired
	private ITraderRepository traderRepository;
	
	@Autowired
	private ITraderCredentialsRepository traderCredentialsRepository;

//	@Override
//	public Trader createAndSaveTrader(Trader trader) {
//		
//		System.out.println("TraderServiceImpl createAndSaveTrader");
//		
//	     System.out.println("0000000000000000000000000000000000000");
//	
//	    if (trader.getCredentials() != null) {
//	        //trader.getCredentials().setTrader(trader);
//	        System.out.println("11111111111111111111111111111111111111");
//	    }
//	    
//	    System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
//	    
//	    System.out.println(trader.toString());
//	    System.out.println(trader.getCredentials().toString());
//		
//		return traderRepository.save(trader);
////		return null;
//	}
	
	
	
//	public Trader addTrader(Trader trader) {
//        return traderRepository.save(trader);
//    }
	
	
	 
	    public Trader addTrader(Trader trader) {
	        // Save the trader first
	        Trader savedTrader = traderRepository.save(trader);
	        
	        // Save the credentials with the trader reference
	        if (savedTrader.getCredentials() != null) {
	            TraderCredentials credentials = savedTrader.getCredentials();
	            credentials.setTrader(savedTrader);
	            
				traderCredentialsRepository.save(credentials);
	        }

	        return savedTrader;
	    }
	
	

}
