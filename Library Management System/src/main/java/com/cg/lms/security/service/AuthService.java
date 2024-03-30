package com.cg.lms.security.service;

import com.cg.lms.dto.Account;
import com.cg.lms.dto.Login;
import com.cg.lms.security.exception.InvalidCredentialsException;
import com.cg.lms.security.exception.SessionTimedOutException;
import com.cg.lms.security.exception.UserCollisionException;

public interface AuthService {

	String authenticateAccount(Login credentials) throws InvalidCredentialsException;

	String createNewAccount(Account newUser) throws UserCollisionException;

	Account changePassword(String email, String password);
	
	boolean isValidAccount(String bearerToken);

	Account loadAccountFromToken(String authorizationToken) throws SessionTimedOutException;
}
