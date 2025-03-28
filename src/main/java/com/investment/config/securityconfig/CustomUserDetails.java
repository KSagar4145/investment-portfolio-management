package com.investment.config.securityconfig;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.investment.pojos.User;


public class CustomUserDetails implements UserDetails {
	
	private String email;
	private String password;
	List<GrantedAuthority> authorities;
	
	public CustomUserDetails(User user) {
		this.email=user.getEmail();
		this.password=user.getPassword();
		this.authorities=Arrays.stream(user.getRole().name().split(","))
				.map(role->new SimpleGrantedAuthority(role))
				.collect(Collectors.toList());
	}
	

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return email;
	}
	
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	
	
	@Override
	public boolean isEnabled() {
		return true;
	}
	
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

}
