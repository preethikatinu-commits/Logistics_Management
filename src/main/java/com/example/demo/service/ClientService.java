package com.example.demo.service;
import java.util.List;

import com.example.demo.dto.ClientDto ;
public interface ClientService {
    
    ClientDto createClient(ClientDto clientDto);

    ClientDto getClientById(Long id);

    List<ClientDto> getAllClients();

    ClientDto updateClient(Long id, ClientDto clientDto);

    void deleteClient(Long id);

	List<ClientDto> createClients(List<ClientDto> clientDtos);
}



