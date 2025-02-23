package com.investment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
	
	
	
//	@PostMapping
//	public Trader createAndSaveTrader(@RequestBody Trader trader) {
//		System.out.println("TraderController createAndSaveTrader ");/*trader.toString()*/
//		System.out.println(trader.toString());
//		return traderService.createAndSaveTrader(trader);
//	}
	
	
//	 @PostMapping
//	    public ResponseEntity<Trader> createTrader(@RequestBody Trader trader) {
//	        Trader savedTrader = traderService.saveTrader(trader);
//	        return ResponseEntity.status(HttpStatus.CREATED).body(savedTrader);
//	    }
	
	
	@PostMapping
    public ResponseEntity<Trader> addTrader(@RequestBody Trader trader) {
        Trader savedTrader = traderService.addTrader(trader);
        return ResponseEntity.ok(savedTrader);
    }
	
	
	
	

}
