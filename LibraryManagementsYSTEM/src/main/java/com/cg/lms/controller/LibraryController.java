package com.cg.lms.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.lms.dto.Account;
import com.cg.lms.dto.Author;
import com.cg.lms.dto.Book;
import com.cg.lms.dto.IssuedItem;
import com.cg.lms.exception.BookAlreadyExistException;
import com.cg.lms.exception.BookNotFoundException;
import com.cg.lms.exception.IssuedItemNotFoundException;
import com.cg.lms.exception.NotEligibleForIssueException;
import com.cg.lms.repo.AccountRepository;
import com.cg.lms.repo.AuthorRepository;
import com.cg.lms.repo.BookRepository;
import com.cg.lms.repo.IssuedItemRepository;
import com.cg.lms.repo.PersonRepository;
import com.cg.lms.service.AccountService;
import com.cg.lms.service.BookService;
import com.cg.lms.service.IssueService;

@RestController
@RequestMapping(value = "/library")
public class LibraryController {

	private BookService bookService;
	private IssueService issueService;
	private AccountService accountService;
	
	@Autowired
	private AuthorRepository authorRepo;
	
	
	@Autowired	
	public LibraryController(BookService bookService, IssueService issueService, AccountService accountService) {
		super();
		this.bookService = bookService;
		this.issueService = issueService;
		this.accountService = accountService;
	}

	/**
	 * Find Books by title
	 * @param title
	 * @return
	 * @throws BookNotFoundException
	 */
	@GetMapping(value = "/bytitle/{title}")
	public List<Book> getByTitles(@PathVariable("title") String title) throws BookNotFoundException{
		return bookService.fetchByTitle(title);
	}
	
	/**
	 * Save new Books
	 * @param book
	 * @return
	 * @throws BookAlreadyExistException
	 */
	@PostMapping(value = "/savebook", consumes = "application/json")
	public Book saveBook(@RequestBody Book book) throws BookAlreadyExistException {
		return bookService.persistBook(book);
	}
	
	/**
	 * Save new Account
	 * @param account
	 * @return
	 */
	@PostMapping(value = "/saveaccount")
	public Account saveAccount(@RequestBody Account account) {
		return accountService.persistAccount(account);
	}	
	
	 /**
	  * Fetch all books on library
	  * @return
	  * @throws BookNotFoundException
	  */
	 @GetMapping(value = "getallbooks", produces = "application/json" )
	 public List<Book> getAllBooks() throws BookNotFoundException{
		 return bookService.fetchAllBooks();
	 }
	
	 
	 /**
	  * Fetch all books by author name
	  * @param name
	  * @return
	  * @throws BookNotFoundException
	  */
	 @GetMapping(value = "getbyauthor/{name}", produces = "application/json")
	 public List<Book> getBookByAuthor(@PathVariable("name") String name) throws BookNotFoundException{
		 return bookService.fetchByAuthor(name);
	 }
	 
	 /**
	  * Fetch all authors by book name
	  * @param title
	  * @return
	  */
	 @GetMapping(value = "getauthorbybook/{title}", produces = "application/json")
	 public List<Author> getAuthorByBook(@PathVariable("title") String title){
		 return authorRepo.findAuthorByBook(title);
	 }
	 
	 /**
	  * Update no of copies
	  * @param isbn
	  * @param copies
	  * @return
	  * @throws BookNotFoundException
	  */
	 @GetMapping(value = "update-book-copies/{bookid}/{copies}")
	 public boolean updateBookCopies(@PathVariable("bookId") int bookId, @PathVariable("copies") int copies) throws BookNotFoundException {
		 return bookService.updateBookCopies(copies, bookId);
	 }
	 
	 @PostMapping(value = "issue-book")
	 public IssuedItem issueBook(@RequestBody IssuedItem item) throws NotEligibleForIssueException, IssuedItemNotFoundException, BookNotFoundException {
		 return issueService.persistIssuedItem(item);
	 }
	 
	 @PostMapping(value = "return-book")
	 public IssuedItem returnBook(@RequestBody IssuedItem item) throws IssuedItemNotFoundException, BookNotFoundException {
		 return issueService.persistReturnedItem(item);
	 }
	 
	 @GetMapping(value = "find-by-issueid/{issueid}")
	 public IssuedItem findIssuedItemByIssueId(@PathVariable("issueid") int issueid) throws IssuedItemNotFoundException {
		 return issueService.findByIssueId(issueid);
	 }
	 
	 @GetMapping(value = "issued-books-by-account-id/{accountid}")
	 public List<IssuedItem> fetchIssuedBooksByAccountId(@PathVariable("accountid") int accountid){
		 return issueService.findByAccountID(accountid);
	 }
}
