package com.cg.lms.service;

import java.util.List;

import com.cg.lms.dto.IssuedItem;
import com.cg.lms.exception.BookNotFoundException;
import com.cg.lms.exception.IssuedItemNotFoundException;
import com.cg.lms.exception.NotEligibleForIssueException;

public interface IssueService {
	
	IssuedItem persistIssuedItem(IssuedItem item) throws NotEligibleForIssueException, IssuedItemNotFoundException, BookNotFoundException;
	IssuedItem persistReturnedItem(IssuedItem item) throws IssuedItemNotFoundException, BookNotFoundException;
	IssuedItem findByIssueId(int issueId) throws IssuedItemNotFoundException;
	List<IssuedItem> findByAccountID(int accountId);
	Double getFine(int issueId);
	

}
