package com.akash.lms.enitities;


import jakarta.persistence.*;

@Entity
@Table(name = "account")
public class Account {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "account_id")
	private int accountId;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="person_id")
	private Person person;
	
	@Column(length = 25, unique = true)
	private String email;
	
	@Column(nullable = false)
	private String password;
	
	@Column(length = 10)
	@Enumerated(EnumType.STRING)
	private Role role;

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "Account [accountId=" + accountId + ", person=" + person + ", email=" + email + ", password=" + password
				+ ", role=" + role + "]";
	}

	public Account(int accountId, Person person, String email, String password, Role role) {
		super();
		this.accountId = accountId;
		this.person = person;
		this.email = email;
		this.password = password;
		this.role = role;
	}
	
	public Account(){
		
	}
	

}
