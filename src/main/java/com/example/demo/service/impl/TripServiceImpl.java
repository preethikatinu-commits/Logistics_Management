package com.example.demo.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.demo.dto.TripDTO;
import com.example.demo.mapper.TripMapper;
import com.example.demo.model.Trip;
import com.example.demo.model.Vehicle;
import com.example.demo.repository.TripRepository;
import com.example.demo.repository.VehicleRepository;
import com.example.demo.service.TripService;

@Service
public class TripServiceImpl implements TripService{ 

	private final TripRepository tripRepository;
	private final VehicleRepository vehicleRepository;
	
	public TripServiceImpl(TripRepository tripRepository, VehicleRepository vehicleRepository) {
		this.tripRepository = tripRepository;
		this.vehicleRepository = vehicleRepository;
		
	}
	
	@Override
	public TripDTO createTrip(TripDTO tripDTO) {
		Vehicle vehicle = vehicleRepository.findById(tripDTO.getVehicleId())
				.orElseThrow(() -> new RuntimeException("Vehicle not found"));
		
		Trip trip = TripMapper.toEntity(tripDTO , vehicle);
		Trip saved = tripRepository.save(trip);
		return TripMapper.toDTO(saved);
	}
	
	@Override
	public  List<TripDTO> getAllTrips(){
		return tripRepository.findAll().stream()
				.map(TripMapper::toDTO)
				.collect(Collectors.toList());
	}
	
	@Override
	public TripDTO getTripById(Long id) {
		return tripRepository.findById(id)
				.map(TripMapper::toDTO)
				.orElseThrow(() -> new RuntimeException("Trip not found"));
	}
	
	@Override
	public void deleteTrip(Long id) {
		tripRepository.deleteById(id);
	}
	
	
}
