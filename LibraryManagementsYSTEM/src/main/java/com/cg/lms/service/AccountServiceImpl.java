package com.cg.lms.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.lms.dto.Account;
import com.cg.lms.dto.Status;
import com.cg.lms.repo.AccountRepository;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountRepository accountRepo;
	
	@Override
	public Account persistAccount(Account account) {
		return accountRepo.save(account);
	}

	@Override
	public Account login(String email, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account forgotPassowrd(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
