package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.ShipmentDTO;

public interface ShipmentService {

	ShipmentDTO createShipment(ShipmentDTO shipmentDTO);
	List<ShipmentDTO> getAllShipments();
	ShipmentDTO getShipmentById(Long id);
	void deleteShipment(Long id);
}
