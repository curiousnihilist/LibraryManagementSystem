package com.cg.lms.service;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.lms.dto.Book;
import com.cg.lms.dto.IssuedItem;
import com.cg.lms.dto.Status;
import com.cg.lms.exception.BookNotFoundException;
import com.cg.lms.exception.IssuedItemNotFoundException;
import com.cg.lms.exception.NotEligibleForIssueException;
import com.cg.lms.repo.AccountRepository;
import com.cg.lms.repo.BookRepository;
import com.cg.lms.repo.IssuedItemRepository;

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
	public List<IssuedItem> findByAccountID(int accountId) {
		return itemRepo.fetchBooksByAccountId(accountId);
	}

	
	@Override
	public Double getFine(int accountId) {
		return itemRepo.fetchIssuedBooks(accountId).stream().map(item -> item.getFine()).collect(Collectors.toList())
					.stream().reduce((double) 0,(x,y) -> x+y);
		
	}

}
