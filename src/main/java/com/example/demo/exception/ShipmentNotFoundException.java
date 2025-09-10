package com.example.demo.exception;

public class ShipmentNotFoundException extends RuntimeException{
	
	public ShipmentNotFoundException(Long id) {
		super("Shipment not found with id:" +id);
	}

}
