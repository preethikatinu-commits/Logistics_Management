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

import com.example.demo.dto.TripDTO;
import com.example.demo.service.TripService;

@RestController
@RequestMapping("/trips")
public class TripController {
	
	private final TripService tripservice;
	
	public TripController(TripService tripservice) {
		this.tripservice = tripservice;
	}
	
	@PostMapping
	public ResponseEntity<TripDTO> createTrip(@RequestBody TripDTO dto){
		return ResponseEntity.ok(tripservice.createTrip(dto));
	}
	
	@GetMapping
	public ResponseEntity<List<TripDTO>> getAllTrips(){
		return ResponseEntity.ok(tripservice.getAllTrips());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<TripDTO> getTripById(@PathVariable Long id){
		return ResponseEntity.ok(tripservice.getTripById(id));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteTrip(@PathVariable Long id){
		tripservice.deleteTrip(id);
		return ResponseEntity.noContent().build();
	}

}
