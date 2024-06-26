package com.akash.lms.repo;

import com.akash.lms.enitities.IssuedItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface IssuedItemRepository extends JpaRepository<IssuedItem, Integer> {
	

	@Query("SELECT i FROM IssuedItem i JOIN FETCH i.account a where a.accountId =?1 AND i.status = 'ISSUED'")
	List<IssuedItem> fetchIssuedBooks(int accountId);
	
	@Query("SELECT i FROM IssuedItem i JOIN FETCH i.account a where a.accountId =?1")
	List<IssuedItem> fetchBooksByAccountId(int accountId);
	
	@Query("SELECT i FROM IssuedItem i JOIN FETCH i.account a where a.accountId =?1 AND i.status = 'RETURNED'")
	List<IssuedItem> fetchReturnedBooksByAccountId(int accountId);
	
	

}
