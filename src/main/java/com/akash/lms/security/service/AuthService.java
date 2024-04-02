package com.akash.lms.security.service;


import com.akash.lms.enitities.Account;
import com.akash.lms.exception.AccountNotFoundException;
import com.akash.lms.security.exception.SessionTimedOutException;
import com.akash.lms.security.exception.UserCollisionException;

public interface AuthService {

	String createNewAccount(Account newUser) throws UserCollisionException;

	String changePassword(String email, String password) throws AccountNotFoundException;
	
	boolean isValidAccount(String bearerToken);

	Account loadAccountFromToken(String authorizationToken) throws SessionTimedOutException;
}
