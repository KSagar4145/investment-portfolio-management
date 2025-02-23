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


	// Method to fetch credentialsId using email and password
	@Transactional(readOnly = true)
	public Long getCredentialsId(String email, String password) {
		TraderCredentials credentials = traderCredentialsRepository.findByTraderEmailAndTraderPassword(email, password)
				.orElseThrow(() -> new RuntimeException("Invalid email or password"));
		return credentials.getCredentialsId();
	}



}
