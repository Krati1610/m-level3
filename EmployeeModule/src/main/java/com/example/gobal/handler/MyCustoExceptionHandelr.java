package com.example.gobal.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.exception.EmployeeNotFoundException;

@RestControllerAdvice
public class MyCustoExceptionHandelr {

	@ExceptionHandler(EmployeeNotFoundException.class)
	public ResponseEntity<String> handleEployeeNotFoundException(EmployeeNotFoundException e) {

		return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

	}
}