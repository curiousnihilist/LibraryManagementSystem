package com.cg.lms.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cg.lms.dto.Book;
import com.cg.lms.dto.IssuedItem;

@Repository
public interface IssuedItemRepository extends JpaRepository<IssuedItem, Integer>{
	
	@Query("SELECT i FROM IssuedItem i JOIN FETCH i.account a where a.accountId =?1 AND i.status = 'ISSUED'")
	List<IssuedItem> fetchIssuedBooks(int accountId);
	
	@Query("SELECT i FROM IssuedItem i JOIN FETCH i.account a where a.accountId =?1")
	List<IssuedItem> fetchBooksByAccountId(int accountId);
	
	@Query("SELECT i FROM IssuedItem i JOIN FETCH i.account a where a.accountId =?1 AND i.status = 'RETURNED'")
	List<IssuedItem> fetchReturnedBooksByAccountId(int accountId);
	
	

}
