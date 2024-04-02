package com.akash.lms.service;


import com.akash.lms.enitities.Book;
import com.akash.lms.exception.BookAlreadyExistException;
import com.akash.lms.exception.BookNotFoundException;

import java.util.List;
import java.util.Optional;

public interface BookService {
	
	Book persistBook(Book book) throws BookAlreadyExistException;
	boolean updateBookCopies(int copies, int bookId) throws BookNotFoundException;
	Book deleteBookById(int bookId) throws BookNotFoundException;
	List<Book> fetchAllBooks() throws BookNotFoundException;
	Optional<Book> fetchBookById(int bookId) throws BookNotFoundException;
	List<Book> fetchByTitle(String title) throws BookNotFoundException;
	List<Book> fetchByIsbn(String isbn) throws BookNotFoundException;
	List<Book> fetchByPublisher(String publisher) throws BookNotFoundException;
	List<Book> fetchByCategory(String category) throws BookNotFoundException;
	List<Book> fetchByAuthor(String Author) throws BookNotFoundException;
	
	

}
