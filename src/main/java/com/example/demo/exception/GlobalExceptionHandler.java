package com.example.demo.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class GlobalExceptionHandler {
	
	
	 @ExceptionHandler(ResourceNotFoundException.class)
	    public ResponseEntity<ErrorResponse> handleResourceNotFound(ResourceNotFoundException ex) {
	        ErrorResponse error = new ErrorResponse(
	                LocalDateTime.now(),
	                ex.getMessage(),
	                "Resource not found"
	        );
	        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	    }

	    // Handle Validation Errors
	    @ExceptionHandler(MethodArgumentNotValidException.class)
	    public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
	        String message = ex.getBindingResult().getFieldErrors().stream()
	                .map(error -> error.getField() + ": " + error.getDefaultMessage())
	                .findFirst()
	                .orElse("Validation error");

	        ErrorResponse error = new ErrorResponse(
	                LocalDateTime.now(),
	                message,
	                "Validation failed"
	        );
	        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	    }

	    // Handle All Other Exceptions
	    @ExceptionHandler(Exception.class)
	    public ResponseEntity<ErrorResponse> handleGlobalException(Exception ex) {
	        ErrorResponse error = new ErrorResponse(
	                LocalDateTime.now(),
	                ex.getMessage(),
	                "Internal Server Error"
	        );
	        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	    }


}
