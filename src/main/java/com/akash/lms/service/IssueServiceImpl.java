package com.akash.lms.service;


import com.akash.lms.dto.Status;
import com.akash.lms.enitities.Book;
import com.akash.lms.enitities.IssuedItem;
import com.akash.lms.exception.BookNotFoundException;
import com.akash.lms.exception.IssuedItemNotFoundException;
import com.akash.lms.exception.NotEligibleForIssueException;
import com.akash.lms.repo.AccountRepository;
import com.akash.lms.repo.BookRepository;
import com.akash.lms.repo.IssuedItemRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class IssueServiceImpl implements IssueService{
	
	private BookRepository bookRepo;
	private AccountRepository accountRepo;
	private IssuedItemRepository itemRepo;
	private BookService bookService;
	
	@Autowired
	public IssueServiceImpl(BookRepository bookRepo, AccountRepository accountRepo, IssuedItemRepository itemRepo,BookService bookService) {
		super();
		this.bookRepo = bookRepo;
		this.accountRepo = accountRepo;
		this.itemRepo = itemRepo;
		this.bookService = bookService;
	}

	@Override
	public IssuedItem persistIssuedItem(IssuedItem item) throws NotEligibleForIssueException, IssuedItemNotFoundException, BookNotFoundException {
		
		Optional<Book> issueingBook = bookRepo.findById(item.getBook().getBookId());
		
		if(issueingBook.isPresent()) {
			int noOfIssuedBooks = itemRepo.fetchIssuedBooks(item.getAccount().getAccountId()).size();
			int copiesOfIssuingBook = issueingBook.get().getNoOfCopies();
			
			if(noOfIssuedBooks < 4 && copiesOfIssuingBook > 0) {
				item.setDueDate(item.getIssueDate().plusDays(30));
				item.setStatus(Status.ISSUED);
				bookService.updateBookCopies(copiesOfIssuingBook-1, item.getBook().getBookId());
				return itemRepo.save(item);
			}
			else
				throw new NotEligibleForIssueException("Cannot issue more books!");
		}
		else
			throw new IssuedItemNotFoundException("No Books for issue!");
		
		
	}

	@Override
	public IssuedItem persistReturnedItem(IssuedItem item) throws IssuedItemNotFoundException, BookNotFoundException {
		if(item.getStatus() == Status.RETURNED)
			throw new IssuedItemNotFoundException("Item is already returned!");
		
		if(itemRepo.existsById(item.getIssueId())) {
			bookService.updateBookCopies(item.getBook().getNoOfCopies()+1, item.getBook().getBookId());
			item.setReturnDate(LocalDate.now());
			if(item.getStatus() == Status.ISSUED && item.getDueDate().isBefore(LocalDate.now()))
				item.setFine(ChronoUnit.DAYS.between( item.getDueDate(),LocalDate.now()));
			item.setStatus(Status.RETURNED);
			return itemRepo.save(item);
		}
		else
			throw new IssuedItemNotFoundException("No Issued item found by the id: "+ item.getIssueId());
		
	}

	@Override
	public IssuedItem findByIssueId(int issueId) throws IssuedItemNotFoundException {
		Optional<IssuedItem> optionalItem = itemRepo.findById(issueId);
		if(optionalItem.isPresent()) {
			IssuedItem item = optionalItem.get();
			if(item.getStatus() == Status.ISSUED && item.getDueDate().isBefore(LocalDate.now()))
				item.setFine(ChronoUnit.DAYS.between( item.getDueDate(),LocalDate.now()));
			return item;
		}
		else
			throw new IssuedItemNotFoundException("No Issued item found by the id: "+ issueId);
	}
	
	@Override
	public Double getFine(int accountId) throws IssuedItemNotFoundException {
		List<IssuedItem> issuedItems = itemRepo.fetchIssuedBooks(accountId);
		if(issuedItems.isEmpty())
			throw new IssuedItemNotFoundException("No Books Issued Currently!");
		return itemRepo.fetchIssuedBooks(accountId).stream().map(item -> item.getFine()).collect(Collectors.toList())
					.stream().reduce((double) 0,(x,y) -> x+y);
		
	}
	
	@Override
	public IssuedItem findIssuedItemById(int issueId) throws IssuedItemNotFoundException {
		Optional<IssuedItem> issuedItem = itemRepo.findById(issueId);
		if(!issuedItem.isPresent())
			throw new IssuedItemNotFoundException("Issued Item not found for id: "+issueId);
		return issuedItem.get();
	}

	@Override
	public List<IssuedItem> findBooksToReturnByAccountId(int accountId) throws IssuedItemNotFoundException {
		List<IssuedItem> issuedItems = itemRepo.fetchIssuedBooks(accountId);
		for(IssuedItem item: issuedItems) {
			if(item.getStatus() == Status.ISSUED && item.getDueDate().isBefore(LocalDate.now()))
				item.setFine(ChronoUnit.DAYS.between( item.getDueDate(),LocalDate.now()));
		}
		if(issuedItems.isEmpty())
			throw new IssuedItemNotFoundException("No Books Issued Currently!");
		return issuedItems;
	}

	@Override
	public List<IssuedItem> findBooksByAccountId(int accountId) throws IssuedItemNotFoundException {
		List<IssuedItem> issuedItems = itemRepo.fetchBooksByAccountId(accountId);
		if(issuedItems.isEmpty())
			throw new IssuedItemNotFoundException("No Books Issued!");
		return issuedItems;
	}

	@Override
	public List<IssuedItem> findReturnedBooksByAccountId(int accountId) throws IssuedItemNotFoundException {
		List<IssuedItem> issuedItems = itemRepo.fetchReturnedBooksByAccountId(accountId);
		if(issuedItems.isEmpty())
			throw new IssuedItemNotFoundException("No Books Issued!");
		return issuedItems;
	}

}
