package com.akash.lms.security.exception;

public class UserCollisionException extends Exception{

	public UserCollisionException() {
		super();
	}

	public UserCollisionException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public UserCollisionException(String message, Throwable cause) {
		super(message, cause);
	}

	public UserCollisionException(String message) {
		super(message);
	}

	
}
