package com.cg.lms.dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "author")
public class Author {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "author_id")
	private int authorId;
	
	@Column(length = 20)
	private String name;
	
	private String description;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "authors")
	private Set<Book> books = new HashSet<>();

	public int getAuthorId() {
		return authorId;
	}

	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Book> getBooks() {
		return books;
	}

	public void setBooks(Set<Book> books) {
		this.books = books;
	}

//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + authorId;
//		result = prime * result + ((books == null) ? 0 : books.hashCode());
//		result = prime * result + ((description == null) ? 0 : description.hashCode());
//		result = prime * result + ((name == null) ? 0 : name.hashCode());
//		return result;
//	}
//
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		Author other = (Author) obj;
//		if (authorId != other.authorId)
//			return false;
//		if (books == null) {
//			if (other.books != null)
//				return false;
//		} else if (!books.equals(other.books))
//			return false;
//		if (description == null) {
//			if (other.description != null)
//				return false;
//		} else if (!description.equals(other.description))
//			return false;
//		if (name == null) {
//			if (other.name != null)
//				return false;
//		} else if (!name.equals(other.name))
//			return false;
//		return true;
//	}

	@Override
	public String toString() {
		return "Author [authorId=" + authorId + ", name=" + name + ", description=" + description + ", books=" + books
				+ "]";
	}

	public Author(int authorId, String name, String description, Set<Book> books) {
		super();
		this.authorId = authorId;
		this.name = name;
		this.description = description;
		this.books = books;
	}
	
	Author(){
		
	}
	

}
