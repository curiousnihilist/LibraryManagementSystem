package com.akash.lms.service;


import com.akash.lms.enitities.IssuedItem;
import com.akash.lms.exception.BookNotFoundException;
import com.akash.lms.exception.IssuedItemNotFoundException;
import com.akash.lms.exception.NotEligibleForIssueException;

import java.util.List;

public interface IssueService {
	
	IssuedItem persistIssuedItem(IssuedItem item) throws NotEligibleForIssueException, IssuedItemNotFoundException, BookNotFoundException;
	IssuedItem persistReturnedItem(IssuedItem item) throws IssuedItemNotFoundException, BookNotFoundException;
	IssuedItem findByIssueId(int issueId) throws IssuedItemNotFoundException;
	Double getFine(int issueId) throws IssuedItemNotFoundException;
	List<IssuedItem> findBooksToReturnByAccountId(int accountId) throws IssuedItemNotFoundException;
	List<IssuedItem> findBooksByAccountId(int accountId) throws IssuedItemNotFoundException;
	List<IssuedItem> findReturnedBooksByAccountId(int accountId) throws IssuedItemNotFoundException;
	IssuedItem findIssuedItemById(int issueId) throws IssuedItemNotFoundException;

}
