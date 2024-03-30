package com.cg.lms.service;

import com.cg.lms.dto.Account;
import com.cg.lms.dto.Status;

public interface AccountService {
	
	Account persistAccount(Account account);
	Account login(String email, String password);
	Account forgotPassowrd(String email);
	

}
