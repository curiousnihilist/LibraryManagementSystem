package com.cg.lms.dto;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "issued_item")
public class IssuedItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "issue_id")
	private int issueId;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "book_id", unique = true)
	private Book book;
	
	@Column(name = "issue_date")
	private LocalDate issueDate;
	
	@Column(name = "due_date")
	private LocalDate dueDate;
	
	@Column(name = "return_date")
	private LocalDate returnDate;
	
	private double fine;
	
	@Column(length = 10)
	private String status;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "account_id", unique = true)
	private Account account;
	

}
