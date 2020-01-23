package com.cg.lms.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.lms.dto.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer>{
	
	List<Book> findByTitleIgnoreCase(String title);
	List<Book> findByPublisherIgnoreCase(String publisher);
	List<Book> findByCategoryContainingIgnoreCase(String category);
	List<Book> findByIsbn(String isbn);
	List<Book> findByTitleContainingIgnoreCase(String title);
	
	@Query("SELECT b FROM Book b JOIN FETCH b.authors a where a.name LIKE ?1")
	List<Book> findByAuthor(String name);
	
	@Modifying
	@Query("UPDATE Book b SET b.noOfCopies =?1 WHERE b.bookId =?2")
	int updateBookCopies(int copies, int bookId);
	
}
