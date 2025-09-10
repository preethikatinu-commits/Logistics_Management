package com.example.demo.exception;

public class TripNotFoundException extends RuntimeException{
	
	public TripNotFoundException(Long id) {
		super("Trip not found with id : " +id);
	}

}
