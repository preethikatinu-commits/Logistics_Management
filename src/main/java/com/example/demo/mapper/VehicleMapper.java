package com.example.demo.mapper;

import com.example.demo.dto.VehicleDTO;
import com.example.demo.model.Vehicle;

public class VehicleMapper {

	public static VehicleDTO toDTO(Vehicle vehicle) {
		VehicleDTO dto = new VehicleDTO();
		dto.setVehicleId(vehicle.getVehicleId());
		dto.setVehicleNumber(vehicle.getVehicleNumber());
		dto.setType(vehicle.getType());
		dto.setCapacity(vehicle.getCapacity());
		dto.setStatus(vehicle.getStatus());
		return dto;
	}
	
	public static Vehicle toEntity(VehicleDTO dto) {
		Vehicle vehicle = new Vehicle();
		vehicle.setVehicleId(dto.getVehicleId());
		vehicle.setVehicleNumber(dto.getVehicleNumber());
		vehicle.setType(dto.getType());
		vehicle.setCapacity(dto.getCapacity());
		vehicle.setStatus(dto.getStatus());
		return vehicle;
	}
	
}
