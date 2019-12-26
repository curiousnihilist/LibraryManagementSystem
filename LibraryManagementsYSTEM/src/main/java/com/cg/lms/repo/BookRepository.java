package com.cg.lms.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.lms.dto.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer>{
	
	List<Book> findByTitleIgnoreCase(String title);
	List<Book> findByPublisherIgnoreCase(String publisher);
	List<Book> findByCategoryIgnoreCase(String category);
	List<Book> findByIsbnIgnoreCase(String isbn);
	
	List<Book> findByTitleContaining(String title);

}
