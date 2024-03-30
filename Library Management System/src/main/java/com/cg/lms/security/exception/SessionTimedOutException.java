package com.cg.lms.security.exception;

public class SessionTimedOutException extends Exception {

	public SessionTimedOutException() {
		super();
	}

	public SessionTimedOutException(String message, Throwable cause) {
		super(message, cause);
	}

	public SessionTimedOutException(String message) {
		super(message);
	}
	
	

}
