package com.example.demo.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ShipmentDTO;
import com.example.demo.service.ShipmentService;

@RestController
@RequestMapping("/shipment")
public class ShipmentController {
	
	private final ShipmentService shipmentService;
	
	public ShipmentController(ShipmentService shipmentService) {
		this.shipmentService = shipmentService;
	}
	
	@PostMapping
	public ResponseEntity<ShipmentDTO> createShipment(@RequestBody ShipmentDTO dto){
		return ResponseEntity.ok(shipmentService.createShipment(dto));
	}

	@GetMapping
	public ResponseEntity<List<ShipmentDTO>> getAllShipments(){
		return ResponseEntity.ok(shipmentService.getAllShipments());
	}
	
	@GetMapping("{id}")
	public ResponseEntity<ShipmentDTO> getShipmentById(@PathVariable Long id){
		return ResponseEntity.ok(shipmentService.getShipmentById(id));
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Void> deleteShipment(@PathVariable Long id){
		shipmentService.deleteShipment(id);
		return ResponseEntity.noContent().build();
	}
}
