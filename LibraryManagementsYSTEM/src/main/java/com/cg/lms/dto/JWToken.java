package com.cg.lms.dto;

public class JWToken {
	
	String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public JWToken(String token) {
		super();
		this.token = token;
	}
	
	

}
