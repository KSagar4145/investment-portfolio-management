package com.investment.config.securityconfig;

import java.util.Optional;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.investment.pojos.User;
import com.investment.repository.UserRepository;


@Component
public class CustomUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepo;


	@Override
	public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
	    Optional<User> user = Optional.ofNullable(userRepo.findByEmail(userEmail));
	    return user.map(actualUser -> {
	        return new CustomUserDetails(actualUser);
	    }).orElseThrow(() -> {
	        return new UsernameNotFoundException("User Not Found");
	    });
	}


}
