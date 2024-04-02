package com.akash.lms.security.controller;

import com.akash.lms.dto.Login;
import com.akash.lms.enitities.Account;
import com.akash.lms.exception.AccountNotFoundException;
import com.akash.lms.security.exception.UserCollisionException;
import com.akash.lms.security.service.AuthService;
import com.akash.lms.security.service.LMSUsernamePwdAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {

	@Autowired
	private AuthService service;

	@Autowired
	private LMSUsernamePwdAuthenticationProvider provider;
	
//	@PostMapping(value = "/login", consumes = "application/json", produces = "application/json")
//	public JWToken authenticateUser(@RequestBody Login credentials) throws InvalidCredentialsException {
//		return  provider
//}
	@PostMapping(value = "/singup", consumes = "application/json")
	public String createNewAccount(@RequestBody Account newUser) throws UserCollisionException {
		return service.createNewAccount(newUser);
}

	@PostMapping(value = "/forgotpaswword", consumes = "application/json", produces = "application/json")
	public String forgotPassword(@RequestBody Login credentials) throws AccountNotFoundException {
		return service.changePassword(credentials.getEmailId(), credentials.getPassword());
	}
	
}