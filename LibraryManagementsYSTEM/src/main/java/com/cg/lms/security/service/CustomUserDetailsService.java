package com.cg.lms.security.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cg.lms.dto.Account;
import com.cg.lms.security.repo.AuthRepository;

@Service
@Transactional
public class CustomUserDetailsService implements UserDetailsService{

	
	@Autowired
	private AuthRepository repository;

	@Override
	public UserDetails loadUserByUsername(String emailId) throws UsernameNotFoundException {
		Account user = repository.findByEmail(emailId);
		
		if (user == null)
			throw new UsernameNotFoundException("User for given email: " + emailId + " not found.");
		
		return org.springframework.security.core.userdetails.User
				.withUsername(emailId)
				.password(user.getPassword())
				.authorities(user.getRole())
				.accountExpired(false)
		        .accountLocked(false)
		        .credentialsExpired(false)
		        .disabled(false)
		        .build();
	}

}
