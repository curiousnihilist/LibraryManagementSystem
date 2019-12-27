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
import com.cg.lms.dto.Book;
import com.cg.lms.exception.BookNotFoundException;
import com.cg.lms.repo.AccountRepository;
import com.cg.lms.repo.AuthorRepository;
import com.cg.lms.repo.BookRepository;
import com.cg.lms.repo.IssuedItemRepository;
import com.cg.lms.repo.PersonRepository;
import com.cg.lms.service.BookService;

@RestController
@RequestMapping(value = "/library")
public class LibraryController {

	private BookRepository bookRepo;
	private AccountRepository accountRepo;
	private AuthorRepository authorRepo;
	private IssuedItemRepository issuedItemRepo;
	private PersonRepository personRepo;
	
	@Autowired
	public LibraryController(BookRepository bookRepo, AccountRepository accountRepo, AuthorRepository authorRepo,
			IssuedItemRepository issuedItemRepo, PersonRepository personRepo) {
		this.bookRepo = bookRepo;
		this.accountRepo = accountRepo;
		this.authorRepo = authorRepo;
		this.issuedItemRepo = issuedItemRepo;
		this.personRepo = personRepo;
	}

	@Autowired
	private BookService bookSevice;
	
	@GetMapping(value = "/bytitle/{title}")
	public List<Book> getByTitles(@PathVariable("title") String title){
		return bookRepo.findByTitleIgnoreCase(title);
	}
	
	@PostMapping(value = "/savebook")
	public Book saveBook(@RequestBody Book book) {
		return bookRepo.save(book);
	}
	
	@PostMapping(value = "/saveaccount")
	public Account saveBook(@RequestBody Account account) {
		return accountRepo.save(account);
	}
	
	 @GetMapping(value = "/{id}")
	 public Optional<Book> getBookById(@PathVariable("id") int id) {
		 return bookRepo.findById(id);
	 }
	 
	 @GetMapping(value = "getallbooks", produces = "application/json" )
	 public List<Book> getAllBooks() throws BookNotFoundException{
		 return bookSevice.fetchAllBooks();
	 }
	
}
