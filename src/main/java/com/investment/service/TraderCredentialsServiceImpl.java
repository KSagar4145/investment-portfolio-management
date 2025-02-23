package com.investment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.investment.pojos.TraderCredentials;
import com.investment.repository.ITraderCredentialsRepository;


@Service
@Transactional
public class TraderCredentialsServiceImpl implements ITraderCredentialsService{
	
	@Autowired
	private ITraderCredentialsRepository traderCredentialsRepository;

	

	

	

}
