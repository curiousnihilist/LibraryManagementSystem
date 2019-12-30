package com.cg.lms.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.lms.dto.Book;
import com.cg.lms.exception.BookAlreadyExistException;
import com.cg.lms.exception.BookNotFoundException;
import com.cg.lms.repo.BookRepository;

@Service
@Transactional
public class BookServiceImpl implements BookService {
	
	@Autowired
	private BookRepository bookRepo;

	@Override
	public Book persistBook(Book book) throws BookAlreadyExistException{
		if(bookRepo.findAll().contains(book))
			throw new BookAlreadyExistException("Book Already Exist in the library");
		return bookRepo.save(book);
	}
	
	@Override
	public List<Book> fetchAllBooks() throws BookNotFoundException {
		List<Book> books = bookRepo.findAll();
		if (books.isEmpty())
			throw new BookNotFoundException("Sorry! No Books in the Library.");
		return bookRepo.findAll();
	}

	@Override
	public Optional<Book> fetchBookById(int bookId) throws BookNotFoundException {
		Optional<Book> book = bookRepo.findById(bookId);
		if( book == null)
			throw new BookNotFoundException("No Book found by the Book Id: "+bookId);
		return book;
	}

	@Override
	public List<Book> fetchByTitle(String title) throws BookNotFoundException {
		List<Book> books = bookRepo.findByTitleContainingIgnoreCase(title);
		if(books.isEmpty())
			throw new BookNotFoundException("No books found with title: "+title);
		return books;
	}

	@Override
	public List<Book> fetchByIsbn(String isbn) throws BookNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Book> fetchByPublisher(String publisher) throws BookNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Book> fetchByCategory(String category) throws BookNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Book> fetchByAuthor(String name) throws BookNotFoundException {
		List<Book> books = bookRepo.findByAuthor(name);
		if(books.isEmpty())
			throw new BookNotFoundException("No Books found with author: "+name);
		return books;
	}

	@Override
	public Book updateBookCopies(int copies, String isbn) throws BookNotFoundException {
		if(bookRepo.findByIsbn(isbn).isEmpty())
			throw new BookNotFoundException("No Book found with ISBN: "+isbn);
		else 
			return bookRepo.updateBookCopies(copies, isbn);
		
	}

	@Override
	public Book deleteBookById(int bookId) throws BookNotFoundException {
		//if(bookRepo.findB(bookId) == null)
		return null;
	}
	

}
