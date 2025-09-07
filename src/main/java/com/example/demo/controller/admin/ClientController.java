package com.example.demo.controller.admin;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.example.demo.dto.ClientDto;
import com.example.demo.service.ClientService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/clients")   // ✅ Base path for all client APIs
@Tag(name = "Client APIs", description = "APIs for managing Clients")
public class ClientController {
	
	 private final ClientService clientService;

	    public ClientController(ClientService clientService) {
	        this.clientService = clientService;
	    }

	    // Create Client
	    @PostMapping
	    @Operation(summary = "Create a new Client")
	    public ResponseEntity<ClientDto> createClient(@Valid @RequestBody ClientDto clientDto) {
	        ClientDto savedClient = clientService.createClient(clientDto);
	        return new ResponseEntity<>(savedClient, HttpStatus.CREATED);
	    }
	    @PostMapping("/bulk")
	    public ResponseEntity<List<ClientDto>> createClients(@Valid @RequestBody List<ClientDto> clients) {
	        // Save each client and collect the results
	        List<ClientDto> savedClients = clients.stream()
	                .map(clientService::createClient)  // call your service for each client
	                .collect(Collectors.toList());

	        return new ResponseEntity<>(savedClients, HttpStatus.CREATED);
	    }
	    
	    
	    // Get Client by ID
	    @GetMapping("/{id}")
	    @Operation(summary = "Get Client by ID")
	    public ResponseEntity<ClientDto> getClientById(@PathVariable Long id) {
	        ClientDto client = clientService.getClientById(id);
	        return ResponseEntity.ok(client);
	    }

	    // Get all Clients
	    @GetMapping
	    @Operation(summary = "Get all Clients")
	    public ResponseEntity<List<ClientDto>> getAllClients() {
	        return ResponseEntity.ok(clientService.getAllClients());
	    }

	    // Update Client
	    @PutMapping("/{id}")
	    @Operation(summary = "Update Client by ID")
	    public ResponseEntity<ClientDto> updateClient(
	            @PathVariable Long id,
	            @Valid @RequestBody ClientDto clientDto) {
	        ClientDto updatedClient = clientService.updateClient(id, clientDto);
	        return ResponseEntity.ok(updatedClient);
	    }

	    // Delete Client
	    @DeleteMapping("/{id}")
	    @Operation(summary = "Delete Client by ID")
	    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
	        clientService.deleteClient(id);
	        return ResponseEntity.noContent().build();
	    }

}
