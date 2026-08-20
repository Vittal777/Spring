package com.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bo.Users;
import com.repo.LoginRepo;


@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	private final LoginRepo repo;
	
	public CustomUserDetailsService(LoginRepo repo) {
		this.repo = repo;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users user = repo.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("User not found"));
		
		return org.springframework.security.core.userdetails.User
				.withUsername(user.getUsername())
				.password(user.getPassword())
				.roles("USER")
				.build();
	}

}
