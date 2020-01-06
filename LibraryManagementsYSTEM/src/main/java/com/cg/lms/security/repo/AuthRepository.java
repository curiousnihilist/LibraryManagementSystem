package com.cg.lms.security.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cg.lms.dto.Account;

public interface AuthRepository extends JpaRepository<Account, Integer>{
	
	Account findByEmail(String email);
	boolean existsByEmail(String email);
	
	@Modifying
	@Query("UPDATE Account a SET a.password = ?1 WHERE a.email = ?2")
	int changePassword(String password, String email);

}
