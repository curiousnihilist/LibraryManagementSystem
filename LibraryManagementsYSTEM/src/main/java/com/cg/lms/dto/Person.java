package com.cg.lms.dto;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "person")
public class Person {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "person_id")
	private int personId;
	
	@Column(length = 25)
	private String name;
	
	private LocalDate dob;
	
	private String phone;
	
	@Column(length = 10)
	private String gender;
	
	@Column(name = "subscription_date")
	private LocalDate subscriptionDate;

	public Person(int personId, String name, LocalDate dob, String phone, LocalDate subscriptionDate, String gender) {
		super();
		this.personId = personId;
		this.name = name;
		this.dob = dob;
		this.phone = phone;
		this.gender = gender;
		this.subscriptionDate = subscriptionDate;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getPersonId() {
		return personId;
	}

	public void setPersonId(int personId) {
		this.personId = personId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public LocalDate getSubscriptionDate() {
		return subscriptionDate;
	}

	public void setSubscriptionDate(LocalDate subscriptionDate) {
		this.subscriptionDate = subscriptionDate;
	}
	
	public Person() {
		
	}
	
	

}
