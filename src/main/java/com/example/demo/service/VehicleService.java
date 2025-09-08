package com.example.demo.service;

import java.util.List;
import com.example.demo.dto.VehicleDTO;

public interface VehicleService {

    // Create
    VehicleDTO addVehicle(VehicleDTO dto);

    // Read
    List<VehicleDTO> getAllVehicles();
    VehicleDTO getVehicleById(Long id);

    // Update
    VehicleDTO updateVehicle(Long id, VehicleDTO dto);

    // Delete
    void deleteVehicle(Long id);
}
