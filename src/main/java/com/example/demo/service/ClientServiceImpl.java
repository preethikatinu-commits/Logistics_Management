package com.example.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.demo.dto.ClientDto;
import com.example.demo.entity.Client;
import com.example.demo.repository.ClientRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ClientServiceImpl implements ClientService {
	

    private final ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    // ✅ Convert Entity -> DTO
    private ClientDto mapToDto(Client client) {
        return new ClientDto(client.getId(), client.getName(), client.getEmail(), client.getPhone());
    }

    // ✅ Convert DTO -> Entity
    private Client mapToEntity(ClientDto clientDto) {
        Client client = new Client();
        client.setId(clientDto.getId());
        client.setName(clientDto.getName());
        client.setEmail(clientDto.getEmail());
        client.setPhone(clientDto.getPhone());
        return client;
    }
    
    @Override
    public List<ClientDto> createClients(List<ClientDto> clientDtos) {
        return clientDtos.stream()
                         .map(this::createClient)   // reuse existing single-create method
                         .collect(Collectors.toList());
    }

    @Override
    public ClientDto createClient(ClientDto clientDto) {
        Client client = mapToEntity(clientDto);
        Client saved = clientRepository.save(client);
        return mapToDto(saved);
    }

    @Override
    public ClientDto getClientById(Long id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found with id " + id));
        return mapToDto(client);
    }

    @Override
    public List<ClientDto> getAllClients() {
        return clientRepository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ClientDto updateClient(Long id, ClientDto clientDto) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found with id " + id));

        client.setName(clientDto.getName());
        client.setEmail(clientDto.getEmail());
        client.setPhone(clientDto.getPhone());

        Client updated = clientRepository.save(client);
        return mapToDto(updated);
    }

    @Override
    public void deleteClient(Long id) {
        if (!clientRepository.existsById(id)) {
            throw new RuntimeException("Client not found with id " + id);
        }
        clientRepository.deleteById(id);
    }
}
