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

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "book")
public class Book {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "book_id", nullable = false)
	private int bookId;
	
	@Column(length = 50)
	private String isbn;
	
	@Column(length = 50)
	private String title;
	
	@Column(length = 50)
	private String publisher;
	
	@Column(length = 50)
	private String description;
	
	@Column(length = 25)
	private String category;
	
	@Column(name = "rack_id", length = 15)
	private String rackId;
	
	private double price;
	
	@Column(name = "no_of_copies")
	private int noOfCopies;

	//@JsonIgnore
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "book_author", joinColumns = {@JoinColumn(name="book_id", referencedColumnName = "book_id")},
									 inverseJoinColumns = {@JoinColumn(name = "author_id", referencedColumnName = "author_id")})
	private Set<Author> authors = new HashSet<>();


	public Book(int bookId, String isbn, String title, String publisher, String description, String category,
			String rackId, double price, int noOfCopies, Set<Author> authors) {
		super();
		this.bookId = bookId;
		this.isbn = isbn;
		this.title = title;
		this.publisher = publisher;
		this.description = description;
		this.category = category;
		this.rackId = rackId;
		this.price = price;
		this.noOfCopies = noOfCopies;
		this.authors = authors;
	}

	Book() {
		
	}

	public int getBookId() {
		return bookId;
	}


	public void setBookId(int bookId) {
		this.bookId = bookId;
	}


	public String getIsbn() {
		return isbn;
	}


	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getPublisher() {
		return publisher;
	}


	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getCategory() {
		return category;
	}


	public void setCategory(String category) {
		this.category = category;
	}


	public String getRackId() {
		return rackId;
	}


	public void setRackId(String rackId) {
		this.rackId = rackId;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	public int getNoOfCopies() {
		return noOfCopies;
	}


	public void setNoOfCopies(int noOfCopies) {
		this.noOfCopies = noOfCopies;
	}


	public Set<Author> getAuthors() {
		return authors;
	}


	public void setAuthors(Set<Author> authors) {
		this.authors = authors;
	}


	


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((authors == null) ? 0 : authors.hashCode());
		result = prime * result + bookId;
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((isbn == null) ? 0 : isbn.hashCode());
		long temp;
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (authors == null) {
			if (other.authors != null)
				return false;
		} else if (!authors.equals(other.authors))
			return false;
		if (bookId != other.bookId)
			return false;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		if (isbn == null) {
			if (other.isbn != null)
				return false;
		} else if (!isbn.equals(other.isbn))
			return false;
		if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Book [bookId=" + bookId + ", isbn=" + isbn + ", title=" + title + ", publisher=" + publisher
				+ ", description=" + description + ", category=" + category + ", rackId=" + rackId + ", price=" + price
				+ ", noOfCopies=" + noOfCopies + ", authors=" + authors + "]";
	}
	
	

	
}
