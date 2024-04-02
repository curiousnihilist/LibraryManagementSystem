package com.akash.lms.exception;

public class BookAlreadyExistException extends Exception{

	public BookAlreadyExistException() {
		super();
	}

	public BookAlreadyExistException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public BookAlreadyExistException(String message, Throwable cause) {
		super(message, cause);
	}

	public BookAlreadyExistException(String message) {
		super(message);
	}

	public BookAlreadyExistException(Throwable cause) {
		super(cause);
	}
	
	

}
