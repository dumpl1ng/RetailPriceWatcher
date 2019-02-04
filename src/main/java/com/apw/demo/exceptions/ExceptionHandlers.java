package com.apw.demo.exceptions;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;



@ControllerAdvice
public class ExceptionHandlers {
	
	
	//custom handle exceptions
	
	
	@ExceptionHandler(value = {UserServiceException.class})
	public ResponseEntity<Object> handleUserServiceException(UserServiceException ex, WebRequest request){
		ErrorMessage errorMessage = new ErrorMessage(new Date(), ex.getMessage());
		
		return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	//handle all other exceptions
	@ExceptionHandler(value = {Exception.class})
	public ResponseEntity<Object> handleOtherException(UserServiceException ex, WebRequest request){
		ErrorMessage errorMessage = new ErrorMessage(new Date(), ex.getMessage());
		
		return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
