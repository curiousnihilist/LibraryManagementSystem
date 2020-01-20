package com.cg.lms.security.service;

import java.sql.SQLIntegrityConstraintViolationException;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cg.lms.dto.Account;
import com.cg.lms.dto.Login;
import com.cg.lms.security.exception.InvalidCredentialsException;
import com.cg.lms.security.exception.SessionTimedOutException;
import com.cg.lms.security.exception.UserCollisionException;
import com.cg.lms.security.jwt.JWTokenProvider;
import com.cg.lms.security.repo.AuthRepository;

@Service
@Transactional(rollbackFor = {DataIntegrityViolationException.class, SQLIntegrityConstraintViolationException.class, UserCollisionException.class})
public class AuthServiceImpl implements AuthService{

	@Autowired private AuthRepository repository;
	@Autowired private AuthenticationManager authenticationManager;
	@Autowired private JWTokenProvider jwTokenProvider;
	@Autowired private PasswordEncoder passwordEncoder;

	@Override
	public String authenticateAccount(Login credentials) throws InvalidCredentialsException {
		try {
			// Takes care of encoding the password
			Authentication authenticate =  authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(credentials.getEmailId(), credentials.getPassword()));
			System.out.println(authenticate.isAuthenticated());
			return jwTokenProvider.createToken(credentials.getEmailId(), repository.findByEmail(credentials.getEmailId()).getRole());
		} catch (AuthenticationException e) {
			throw new InvalidCredentialsException("Invalid Login Credentials.");
		}
	}

	@Override
	public String createNewAccount(Account newAccount) throws UserCollisionException {
		Account existentAccount = repository.findByEmail(newAccount.getEmail());
		
		if (existentAccount != null)
			throw new UserCollisionException("Account with the Email-ID: " + newAccount.getEmail() + " already exists.");
		newAccount.setPassword(passwordEncoder.encode(newAccount.getPassword()));
		System.out.println(passwordEncoder.encode(newAccount.getPassword()));
		repository.save(newAccount);
		
		return jwTokenProvider.createToken(newAccount.getEmail(), repository.findByEmail(newAccount.getEmail()).getRole());
	}

	
	@Override
	public boolean isValidAccount(String authenticationToken) {
		if (authenticationToken == null) {
			return false;  // Invalid token
		}
		
		return jwTokenProvider.getAuthentication(authenticationToken) != null;
	}

	@Override
	public Account loadAccountFromToken(String authorizationToken) throws SessionTimedOutException {
		if (!isValidAccount(authorizationToken))
			throw new SessionTimedOutException("Session Timed Out. Please Login Again");
		String AccountEmailId = jwTokenProvider.getUsername(authorizationToken);
		Account loggedInAccount = repository.findByEmail(AccountEmailId);
		return loggedInAccount;
	}

	@Override
	public Account changePassword(String email, String password) {
		int i = repository.changePassword(passwordEncoder.encode(password), email);
		if(i>0)
			return repository.findByEmail(email);
		return null;
	}

}
