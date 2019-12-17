package com.cg.lms.dto;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


@Entity
@Table(name = "book")
public class Book {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "book_id", nullable = false)
	private int bookId;
	
	@Column(length = 25)
	private String isbn;
	
	@Column(length = 25)
	private String subject;
	
	@Column(length = 25)
	private String title;
	
	@Column(length = 25)
	private String publisher;
	
	@Column(length = 25)
	private String description;
	
	@Column(length = 25)
	private String category;
	
	@Column(name = "rack_id")
	private int rackId;
	
	private double price;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "book_author", joinColumns = {@JoinColumn(name="book_id", referencedColumnName = "book_id")},
									 inverseJoinColumns = {@JoinColumn(name = "author_id", referencedColumnName = "author_id")})
	private Set<Author> authors = new HashSet<>();
	
}
