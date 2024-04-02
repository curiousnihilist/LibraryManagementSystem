package com.akash.lms.restcontroller;

import com.akash.lms.enitities.Book;
import com.akash.lms.enitities.IssuedItem;
import com.akash.lms.exception.BookAlreadyExistException;
import com.akash.lms.exception.BookNotFoundException;
import com.akash.lms.exception.IssuedItemNotFoundException;
import com.akash.lms.exception.NotEligibleForIssueException;
import com.akash.lms.repo.AuthorRepository;
import com.akash.lms.service.BookService;
import com.akash.lms.service.IssueService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/library")
public class LibraryController {

	private BookService bookService;
	private IssueService issueService;

	@Autowired
	private AuthorRepository authorRepo;
	
	
	@Autowired	
	public LibraryController(BookService bookService, IssueService issueService) {
		super();
		this.bookService = bookService;
		this.issueService = issueService;
	}

	/**
	 * Find Books by title
	 * @param title
	 * @return
	 * @throws BookNotFoundException
	 */
	@GetMapping(value = "/books/search")
	public List<Book> getByTitles(@RequestParam("title") String title) throws BookNotFoundException {
		return bookService.fetchByTitle(title);
	}
	
	/**
	 * Save new Books
	 * @param book
	 * @return
	 * @throws BookAlreadyExistException
	 */
	@PostMapping(value = "/books", consumes = "application/json")
	public Book saveBook(@RequestBody Book book) throws BookAlreadyExistException {
		return bookService.persistBook(book);
	}
	

	 /**
	  * Fetch all books on library
	  * @return
	  * @throws BookNotFoundException
	  */
	 @GetMapping(value = "/books", produces = "application/json" )
	 public List<Book> getAllBooks(HttpServletRequest request) throws BookNotFoundException{
		 System.out.println(request.getUserPrincipal().getName());
		 return bookService.fetchAllBooks();
	 }
	
	 
	 /**
	  * Fetch all books by author name
	  * @param name
	  * @return
	  * @throws BookNotFoundException
	  */
	 @GetMapping(value = "/books/search/author", produces = "application/json")
	 public List<Book> getBookByAuthor(@RequestParam("author") String author) throws BookNotFoundException{
		 return bookService.fetchByAuthor(author);
	 }
	 
	 @GetMapping(value = "/books/search/isbn", produces = "application/json")
	 public List<Book> getBookByIsbn(@RequestParam("isbn") String isbn) throws BookNotFoundException{
		 return bookService.fetchByIsbn(isbn);
	 }
	 
	 @GetMapping(value = "/books/search/category", produces = "application/json")
	 public List<Book> getBookByCategory(@RequestParam("category") String category) throws BookNotFoundException{
		 return bookService.fetchByCategory(category);
	 }
	 
	 /**
	  * Update no of copies
	  * @param isbn
	  * @param copies
	  * @return
	  * @throws BookNotFoundException
	  */
	 @PatchMapping(value = "/books/update/{bookid}")
	 public boolean updateBookCopies(@PathVariable("bookId") int bookId, @RequestParam("copies") int copies) throws BookNotFoundException {
		 return bookService.updateBookCopies(copies, bookId);
	 }
	 
	 @PostMapping(value = "/books/issue")
	 public IssuedItem issueBook(@RequestBody IssuedItem item) throws NotEligibleForIssueException, IssuedItemNotFoundException, BookNotFoundException {
		 return issueService.persistIssuedItem(item);
	 }
	 
	 @PostMapping(value = "/books/return")
	 public IssuedItem returnBook(@RequestBody IssuedItem item) throws IssuedItemNotFoundException, BookNotFoundException {
		 return issueService.persistReturnedItem(item);
	 }
	 
	 @GetMapping(value = "/issues/{issueid}")
	 public IssuedItem findIssuedItemByIssueId(@PathVariable("issueid") int issueid) throws IssuedItemNotFoundException {
		 return issueService.findByIssueId(issueid);
	 }
	 
	 
	 @GetMapping(value = "/issues/{id}")
	 public List<IssuedItem> fetchIssuedBooksId(@PathVariable("id") int accountId) throws IssuedItemNotFoundException{
		 return issueService.findBooksToReturnByAccountId(accountId);
	 }
	 
	 @GetMapping(value = "/issues")
	 public List<IssuedItem> fetchIssuedBooksByAccountId(@RequestParam("accountid") int accountId) throws IssuedItemNotFoundException{
		 return issueService.findBooksToReturnByAccountId(accountId);
	 }
	 
	 @GetMapping(value = "/issues/returns/{accountid}")
	 public List<IssuedItem> fetchReturnedBookByAccountId(@PathVariable("accountid") int accountId) throws IssuedItemNotFoundException{
		 return issueService.findReturnedBooksByAccountId(accountId);
	 }

}
