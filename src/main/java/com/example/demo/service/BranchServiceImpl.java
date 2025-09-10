package com.example.demo.service;



import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.demo.dto.BranchDto;
import com.example.demo.entity.Branch;
import com.example.demo.entity.BranchType;
import com.example.demo.entity.Client;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.BranchRepository;
import com.example.demo.repository.ClientRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class BranchServiceImpl implements BranchService {
	 private final BranchRepository branchRepository;
	    private final ClientRepository clientRepository;

	    public BranchServiceImpl(BranchRepository branchRepository, ClientRepository clientRepository) {
	        this.branchRepository = branchRepository;
	        this.clientRepository = clientRepository;
	    }

	    // ---- Create Single Branch ----
	    @Override
	    public BranchDto createBranch(Long clientId, BranchDto branchDto) {
	        Client client = clientRepository.findById(clientId)
	                .orElseThrow(() -> new ResourceNotFoundException("Client not found with id " + clientId));

	        Branch branch = new Branch();
	        branch.setName(branchDto.getName());
	        branch.setAddress(branchDto.getAddress());
	        branch.setCity(branchDto.getCity());
	        branch.setState(branchDto.getState());
	        branch.setPinCode(branchDto.getPinCode());
	        branch.setClient(client);

	        // ✅ handle type
	        if (branchDto.getType() != null) {
	            branch.setType(BranchType.valueOf(branchDto.getType().toUpperCase()));
	        } else {
	            branch.setType(BranchType.LOCAL);
	        }

	        Branch savedBranch = branchRepository.save(branch);
	        return BranchDto.fromEntity(savedBranch);
	    }

	    // ---- Create Multiple Branches (Bulk) ----
	    @Override
	    public List<BranchDto> createBranches(Long clientId, List<BranchDto> branchDtos) {
	        return branchDtos.stream()
	                .map(dto -> createBranch(clientId, dto))
	                .collect(Collectors.toList());
	    }

	    // ---- Update Branch ----
	    @Override
	    public BranchDto updateBranch(Long branchId, BranchDto branchDto) {
	        Branch branch = branchRepository.findById(branchId)
	                .orElseThrow(() -> new ResourceNotFoundException("Branch not found with id " + branchId));

	        branch.setName(branchDto.getName());
	        branch.setAddress(branchDto.getAddress());
	        branch.setCity(branchDto.getCity());
	        branch.setState(branchDto.getState());
	        branch.setPinCode(branchDto.getPinCode());

	        // ✅ update type
	        if (branchDto.getType() != null) {
	            branch.setType(BranchType.valueOf(branchDto.getType().toUpperCase()));
	        }

	        Branch updatedBranch = branchRepository.save(branch);
	        return BranchDto.fromEntity(updatedBranch);
	    }

	    // ---- Delete Branch ----
	    @Override
	    public void deleteBranch(Long branchId) {
	        Branch branch = branchRepository.findById(branchId)
	                .orElseThrow(() -> new ResourceNotFoundException("Branch not found with id " + branchId));
	        branchRepository.delete(branch);
	    }

	    // ---- Get Branch by ID ----
	    @Override
	    public BranchDto getBranchById(Long branchId) {
	        Branch branch = branchRepository.findById(branchId)
	                .orElseThrow(() -> new ResourceNotFoundException("Branch not found with id " + branchId));
	        return BranchDto.fromEntity(branch);
	    }

	    // ---- Get Branches by Client ----
	    @Override
	    public List<BranchDto> getBranchesByClient(Long clientId) {
	        if (!clientRepository.existsById(clientId)) {
	            throw new ResourceNotFoundException("Client not found with id " + clientId);
	        }

	        return branchRepository.findByClientId(clientId)
	                .stream()
	                .map(BranchDto::fromEntity)
	                .collect(Collectors.toList());
	    }

	    // ---- Get All Branches ----
	    @Override
	    public List<BranchDto> getAllBranches() {
	        return branchRepository.findAll()
	                .stream()
	                .map(BranchDto::fromEntity)
	                .collect(Collectors.toList());
	    }

	    // ---- ✅ New: Get Branches by Type ----
	    @Override
	    public List<BranchDto> getBranchesByType(String type) {
	        BranchType branchType = BranchType.valueOf(type.toUpperCase());
	        return branchRepository.findByType(branchType)
	                .stream()
	                .map(BranchDto::fromEntity)
	                .collect(Collectors.toList());
	    }

	    // ---- ✅ New: Get Branches by Client + Type ----
	    @Override
	    public List<BranchDto> getBranchesByClientAndType(Long clientId, String type) {
	        BranchType branchType = BranchType.valueOf(type.toUpperCase());
	        return branchRepository.findByClientIdAndType(clientId, branchType)
	                .stream()
	                .map(BranchDto::fromEntity)
	                .collect(Collectors.toList());
	    }
}