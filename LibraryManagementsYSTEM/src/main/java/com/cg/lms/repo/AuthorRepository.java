package com.cg.lms.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cg.lms.dto.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer>{
	
	List<Author> findByNameContainingIgnoreCase(String name);
	
	@Query("SELECT a FROM Author a JOIN FETCH a.books b where b.title LIKE ?1")
	List<Author> findAuthorByBook(String title);

}
