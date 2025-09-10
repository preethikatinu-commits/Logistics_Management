package com.example.demo.mapper;

import com.example.demo.dto.TripDTO;
import com.example.demo.model.Trip;
import com.example.demo.model.Vehicle;

public class TripMapper {
	
	public static TripDTO toDTO(Trip trip) {
		TripDTO dto = new TripDTO();
		dto.setTripId(trip.getTripId());
		dto.setSource(trip.getSource());
		dto.setDestination(trip.getDestination());
		dto.setStatus(trip.getStatus());
		
		if (trip.getVehicle() != null) {
            dto.setVehicleId(trip.getVehicle().getVehicleId());
        }
		
		return dto;
	}
	
	public static Trip toEntity(TripDTO dto , Vehicle vehicle) {
		Trip trip = new Trip();
		trip.setTripId(dto.getTripId());
		trip.setSource(dto.getSource());
		trip.setDestination(dto.getDestination());
		trip.setStatus(dto.getStatus());
		
		 trip.setVehicle(vehicle); 
		
		return trip;
	}

}
