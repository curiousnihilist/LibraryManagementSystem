package com.cg.lms.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.lms.dto.Account;
import com.cg.lms.dto.Login;
import com.cg.lms.security.exception.InvalidCredentialsException;
import com.cg.lms.security.exception.UserCollisionException;
import com.cg.lms.security.service.AuthService;

@RestController
@RequestMapping(value = "auth-api")
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private AuthService service;
	
	@PostMapping(value = "/authenticate", consumes = "application/json", produces = "application/json")
	public String authenticateUser(@RequestBody Login credentials) throws InvalidCredentialsException {
		return service.authenticateAccount(credentials);
}
	@PostMapping(value = "/sign-up", consumes = "application/json")
	public String createNewAccount(@RequestBody Account newUser) throws UserCollisionException {
		return service.createNewAccount(newUser);
}

	@PostMapping(value = "/forgot-password", consumes = "application/json", produces = "application/json")
	public Account forgotPassword(@RequestBody Login credentials) {
		return service.changePassword(credentials.getEmailId(), credentials.getPassword());
	}
	
	
}