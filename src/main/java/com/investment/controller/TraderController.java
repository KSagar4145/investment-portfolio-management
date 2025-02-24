package com.investment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.investment.pojos.Trader;
import com.investment.pojos.TraderCredentials;
import com.investment.pojos.TraderCredentials;
import com.investment.service.ITraderService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping("/traders")
public class TraderController {
	
	
	@Autowired
	private ITraderService traderService;
		
	
	@PostMapping
    public ResponseEntity<Trader> addTrader(@RequestBody Trader trader) {
        Trader savedTrader = traderService.addTrader(trader);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTrader);
    }
	
	

	
	
	
	
	
	
	
	

}
