package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.TripDTO;

public interface TripService {
	
	TripDTO createTrip(TripDTO tripDTO);
	List<TripDTO> getAllTrips();
	TripDTO getTripById(Long id);
	void deleteTrip(Long id);

}
