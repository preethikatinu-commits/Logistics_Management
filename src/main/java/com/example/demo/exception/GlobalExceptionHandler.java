package com.example.demo.exception;

import java.time.LocalDateTime;
 //renu-feature01-local

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


//import java.util.HashMap;
//import java.util.Map;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//
//@ControllerAdvice
//public class GlobalExceptionHandler {
//	
//	@ExceptionHandler(VehicleNotFoundException.class)
//	public ResponseEntity<Map<String , Object>> handleVehicleNootFound(VehicleNotFoundException ex){
//		return buildResponse(ex.getMessage(), HttpStatus.NOT_FOUND);
//		
//	}
//	
//	@ExceptionHandler(TripNotFoundException.class)
//    public ResponseEntity<Map<String, Object>> handleTripNotFound(TripNotFoundException ex) {
//        return buildResponse(ex.getMessage(), HttpStatus.NOT_FOUND);
//    }
//
//    @ExceptionHandler(ShipmentNotFoundException.class)
//    public ResponseEntity<Map<String, Object>> handleShipmentNotFound(ShipmentNotFoundException ex) {
//        return buildResponse(ex.getMessage(), HttpStatus.NOT_FOUND);
//    }
//
//    @ExceptionHandler(Exception.class) // fallback for any other exception
//    public ResponseEntity<Map<String, Object>> handleGenericException(Exception ex) {
//        return buildResponse("Internal Server Error: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//
//    private ResponseEntity<Map<String, Object>> buildResponse(String message, HttpStatus status) {
//        Map<String, Object> error = new HashMap<>();
//        error.put("timestamp", LocalDateTime.now());
//        error.put("status", status.value());
//        error.put("error", status.getReasonPhrase());
//        error.put("message", message);
//
//        return new ResponseEntity<>(error, status);
//    }
// 
//
//}
