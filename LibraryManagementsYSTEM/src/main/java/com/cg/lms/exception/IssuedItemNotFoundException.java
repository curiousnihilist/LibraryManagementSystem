package com.cg.lms.exception;

public class IssuedItemNotFoundException extends Exception{

	public IssuedItemNotFoundException() {
		super();
	}

	public IssuedItemNotFoundException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public IssuedItemNotFoundException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public IssuedItemNotFoundException(String arg0) {
		super(arg0);
	}

	public IssuedItemNotFoundException(Throwable arg0) {
		super(arg0);
	}

	@Override
	public synchronized Throwable fillInStackTrace() {
		return super.fillInStackTrace();
	}

	@Override
	public String getMessage() {
		return super.getMessage();
	}

	@Override
	public void printStackTrace() {
		super.printStackTrace();
	}
	
	
	

}
