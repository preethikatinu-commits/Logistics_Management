package com.example.demo.repository;

import java.util.Optional;



import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Client;

import org.springframework.data.domain.Pageable;



public interface ClientRepository extends JpaRepository<Client, Long> {

    // Find client by phone (unique check)
    Optional<Client> findByPhone(String phone);

    // Find client by email (optional, for uniqueness check)
    Optional<Client> findByEmail(String email);

    // Search clients by name (case insensitive)
    Page<Client> findByNameContainingIgnoreCase(String name, Pageable pageable);
}



