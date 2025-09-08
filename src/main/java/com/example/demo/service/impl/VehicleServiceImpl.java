package com.example.demo.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.demo.dto.VehicleDTO;
import com.example.demo.exceptions.VehicleNotFoundException;
import com.example.demo.mapper.VehicleMapper;
import com.example.demo.model.Vehicle;
import com.example.demo.repository.VehicleRepository;
import com.example.demo.service.VehicleService;

@Service
public class VehicleServiceImpl implements VehicleService {

    private static final Logger logger = LoggerFactory.getLogger(VehicleServiceImpl.class);

    private final VehicleRepository vehicleRepository;

    public VehicleServiceImpl(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public VehicleDTO addVehicle(VehicleDTO dto) {
        logger.info("Adding new vehicle: {}", dto);
        Vehicle vehicle = VehicleMapper.toEntity(dto);
        Vehicle saved = vehicleRepository.save(vehicle);
        return VehicleMapper.toDTO(saved);
    }

    @Override
    public List<VehicleDTO> getAllVehicles() {
        List<VehicleDTO> vehicles = vehicleRepository.findAll()
                .stream()
                .map(VehicleMapper::toDTO)
                .toList();

        if (vehicles.isEmpty()) {
            logger.warn("No vehicles found in the database.");
        }
        return vehicles;
    }

    @Override
    public VehicleDTO getVehicleById(Long id) {
        return vehicleRepository.findById(id)
                .map(VehicleMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Vehicle not found with id: " + id));
    }

    @Override
    public VehicleDTO updateVehicle(Long id, VehicleDTO dto) {
        Vehicle existing = vehicleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vehicle not found with id: " + id));

        existing.setVehicleNumber(dto.getVehicleNumber());
        existing.setType(dto.getType());
        existing.setCapacity(dto.getCapacity());
        existing.setStatus(dto.getStatus());

        Vehicle updated = vehicleRepository.save(existing);
        return VehicleMapper.toDTO(updated);
    }

    @Override
    public void deleteVehicle(Long id) {
        if (!vehicleRepository.existsById(id)) {
            throw new RuntimeException("Vehicle not found with id: " + id);
        }
        vehicleRepository.deleteById(id);
        logger.info("Deleted vehicle with id: {}", id);
    }
    
}
