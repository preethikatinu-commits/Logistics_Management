package com.example.demo.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.VehicleDTO;
import com.example.demo.service.VehicleService;

@Controller
@RequestMapping("/vehicles")  
public class VehicleController {

    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    // REST API → add new vehicle
    @PostMapping
    @ResponseBody  // needed to return JSON instead of a view
    public ResponseEntity<VehicleDTO> addVehicle(@RequestBody VehicleDTO vehicleDTO) {
        return ResponseEntity.ok(vehicleService.addVehicle(vehicleDTO));
    }

    // MVC → show vehicles in JSP
    @GetMapping
    public String getVehicles(Model model) {
        List<VehicleDTO> vehicles = vehicleService.getAllVehicles();
        model.addAttribute("vehicleList", vehicles);
        return "vehicle";  // vehicle.jsp in /templates
    }
}
