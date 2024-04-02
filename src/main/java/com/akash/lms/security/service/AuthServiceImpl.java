package com.akash.lms.security.service;

import com.akash.lms.enitities.Account;
import com.akash.lms.exception.AccountNotFoundException;
import com.akash.lms.repo.AccountRepository;
import com.akash.lms.security.exception.SessionTimedOutException;
import com.akash.lms.security.exception.UserCollisionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLIntegrityConstraintViolationException;

@Service
@Transactional(rollbackFor = {DataIntegrityViolationException.class, SQLIntegrityConstraintViolationException.class, UserCollisionException.class})
public class AuthServiceImpl implements AuthService{

	@Autowired
	private AccountRepository repository;
	@Autowired
	private PasswordEncoder passwordEncoder;


	@Override
	public String createNewAccount(Account newAccount) throws UserCollisionException {
		Account existentAccount = repository.findByEmail(newAccount.getEmail());
		if (existentAccount != null)
			throw new UserCollisionException("Account with the email id: " + newAccount.getEmail() + " already exists.");
		newAccount.setPassword(passwordEncoder.encode(newAccount.getPassword()));
		repository.save(newAccount);
		return "User saved with email:"+newAccount.getEmail();
	}

	
	@Override
	public boolean isValidAccount(String authenticationToken) {
//		if (authenticationToken == null) {
//			return false;  // Invalid token
//		}
//		return jwTokenProvider.getAuthentication(authenticationToken) != null;
		return false;
	}

	@Override
	public Account loadAccountFromToken(String authorizationToken) throws SessionTimedOutException {
//		if (!isValidAccount(authorizationToken))
//			throw new SessionTimedOutException("Session Timed Out. Please Login Again");
//		String AccountEmailId = jwTokenProvider.getUsername(authorizationToken);
//		Account loggedInAccount = repository.findByEmail(AccountEmailId);
//		return loggedInAccount;
		return null;
	}

	@Override
	public String changePassword(String email, String password) throws AccountNotFoundException {
		Account existentAccount = repository.findByEmail(email);
		if (existentAccount == null)
			throw new AccountNotFoundException("Account with the email id: " + email + " not found!");
		repository.changePassword(passwordEncoder.encode(password), email);
		return "Password changed for email:"+email;
	}

}
