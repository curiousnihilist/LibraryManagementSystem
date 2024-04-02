package com.akash.lms.repo;

import com.akash.lms.enitities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer>{
	
	List<Author> findByNameContainingIgnoreCase(String name);
	
	@Query("SELECT a FROM Author a JOIN FETCH a.books b where b.title LIKE ?1")
	List<Author> findAuthorByBook(String title);

}
