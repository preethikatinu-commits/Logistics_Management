package com.example.demo.exception;

public class VehicleNotFoundException extends RuntimeException{
	
	public VehicleNotFoundException(Long id) {
		super("Vehicle not found with id: " + id);
	}

}
