package com.akash.lms.restcontroller;

import com.akash.lms.exception.*;
import com.akash.lms.security.exception.InvalidCredentialsException;
import com.akash.lms.security.exception.SessionTimedOutException;
import com.akash.lms.security.exception.UserCollisionException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;


@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler({AccountNotFoundException.class,
					   BookAlreadyExistException.class,
					   BookNotFoundException.class,
					   IssuedItemNotFoundException.class,
					   NotEligibleForIssueException.class,
					   UserCollisionException.class,
					   SessionTimedOutException.class,
					   InvalidCredentialsException.class})
	public ResponseEntity<RestExceptionResponse> exceptionHandler(Exception ex, WebRequest request){
		
		RestExceptionResponse error = new RestExceptionResponse();
		
		error.setTimestamp(LocalDateTime.now());
		error.setStatus(HttpStatus.NOT_FOUND);
		error.setError(ex.getMessage());
		
		return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
	}

}
