package com.akash.lms.repo;


import com.akash.lms.enitities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface AccountRepository extends JpaRepository<Account, Integer>{

	/**
	 * Login Method
	 * @param email, password
	 * @return Account details
	 */
	@Query("FROM Account a WHERE a.email = :email AND a.password = :password")
	Account login(@Param("email") String email,@Param("password") String password);
	
	/**
	 * Forgot Password Method
	 * @param email password
	 * @return Account details
	 * 
	 */
	@Modifying
	@Query("UPDATE Account a SET a.password = :password WHERE a.email = :email")
	Account changePassword(@Param("email") String email,@Param("password") String password);

	Account findByEmail(String email);
	boolean existsByEmail(String email);
	
}
