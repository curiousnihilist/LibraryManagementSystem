package com.cg.lms.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.lms.dto.Account;
import com.cg.lms.dto.JWToken;
import com.cg.lms.dto.Login;
import com.cg.lms.security.exception.InvalidCredentialsException;
import com.cg.lms.security.exception.SessionTimedOutException;
import com.cg.lms.security.exception.UserCollisionException;
import com.cg.lms.security.service.AuthService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "auth-api")
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private AuthService service;
	
	@PostMapping(value = "/authenticate", consumes = "application/json", produces = "application/json")
	public JWToken authenticateUser(@RequestBody Login credentials) throws InvalidCredentialsException {
		return new JWToken(service.authenticateAccount(credentials));
}
	@PostMapping(value = "/sign-up", consumes = "application/json")
	public JWToken createNewAccount(@RequestBody Account newUser) throws UserCollisionException {
		return new JWToken(service.createNewAccount(newUser));
}

	@PostMapping(value = "/forgot-password", consumes = "application/json", produces = "application/json")
	public Account forgotPassword(@RequestBody Login credentials) {
		return service.changePassword(credentials.getEmailId(), credentials.getPassword());
	}
	
	@GetMapping(value = "/get-user", produces = "application/json")
	public Account getUserForToken(@RequestHeader("Authorization") String authorizationToken) throws SessionTimedOutException {
		return service.loadAccountFromToken(authorizationToken);
	}
	
}