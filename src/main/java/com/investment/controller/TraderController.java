package com.investment.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.investment.pojos.Trader;
import com.investment.service.ITraderService;



@RestController
@RequestMapping("/traders")
public class TraderController {
	
	
	@Autowired
	private ITraderService traderService;
	
	@PostMapping("path")
	public Trader createAndSaveTrader(@RequestBody Trader trader) {
		System.out.println("TraderController createAndSaveTrader "+trader.toString());
		return traderService.createAndSaveTrader(trader);
	}
	
	
//	 @PostMapping
//	    public ResponseEntity<Trader> createTrader(@RequestBody Trader trader) {
//	        Trader savedTrader = traderService.saveTrader(trader);
//	        return ResponseEntity.status(HttpStatus.CREATED).body(savedTrader);
//	    }
	

}
