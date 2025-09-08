package com.example.demo.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.demo.dto.ShipmentDTO;
import com.example.demo.mapper.ShipmentMapper;
import com.example.demo.model.Shipment;
import com.example.demo.model.Trip;
import com.example.demo.repository.ShipmentRepository;
import com.example.demo.repository.TripRepository;
import com.example.demo.service.ShipmentService;

@Service
public class ShipmentServiceImpl implements ShipmentService{

	private final ShipmentRepository shipmentRepository;
	private final TripRepository tripRepository;
	
	public ShipmentServiceImpl(ShipmentRepository shipmentRepository, TripRepository tripRepository) {
		this.shipmentRepository = shipmentRepository;
		this.tripRepository = tripRepository;
	}
	
	@Override
	public ShipmentDTO createShipment(ShipmentDTO dto) {
		Trip trip = tripRepository.findById(dto.getTripId())
				.orElseThrow(() -> new RuntimeException("Trip not found"));
		Shipment shipment = ShipmentMapper.toEntity(dto, trip);
		Shipment saved = shipmentRepository.save(shipment);
		return ShipmentMapper.toDTO(saved);
	}
	
	@Override
	public List<ShipmentDTO> getAllShipments(){
		return shipmentRepository.findAll().stream()
				.map(ShipmentMapper::toDTO)
				.collect(Collectors.toList());
	}
	
	@Override
	public ShipmentDTO getShipmentById(Long id) {
		return shipmentRepository.findById(id)
				.map(ShipmentMapper::toDTO)
				.orElseThrow(()-> new RuntimeException("Shipment not found"));
	}
	
	@Override
	public void deleteShipment(Long id) {
		shipmentRepository.deleteById(id);
	}
}
