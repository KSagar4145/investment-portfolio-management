package com.investment.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.investment.pojos.Trader;
import com.investment.service.ITraderService;


@RestController
public class TraderController {
	
	
	@Autowired
	private ITraderService traderService;
	
	@PostMapping("path")
	public Trader createAndSaveTrader(@RequestBody Trader trader) {
		return traderService.createAndSaveTrader(trader);
	}
	
	

}
