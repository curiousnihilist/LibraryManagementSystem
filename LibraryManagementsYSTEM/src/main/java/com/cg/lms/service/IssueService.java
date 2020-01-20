package com.cg.lms.service;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.cg.lms.dto.IssuedItem;
import com.cg.lms.exception.BookNotFoundException;
import com.cg.lms.exception.IssuedItemNotFoundException;
import com.cg.lms.exception.NotEligibleForIssueException;

public interface IssueService {
	
	IssuedItem persistIssuedItem(IssuedItem item) throws NotEligibleForIssueException, IssuedItemNotFoundException, BookNotFoundException;
	IssuedItem persistReturnedItem(IssuedItem item) throws IssuedItemNotFoundException, BookNotFoundException;
	IssuedItem findByIssueId(int issueId) throws IssuedItemNotFoundException;
	Double getFine(int issueId) throws IssuedItemNotFoundException;
	List<IssuedItem> findBooksToReturnByAccountId(int accountId) throws IssuedItemNotFoundException;
	List<IssuedItem> findBooksByAccountId(int accountId) throws IssuedItemNotFoundException;
	List<IssuedItem> findReturnedBooksByAccountId(int accountId) throws IssuedItemNotFoundException;

}
