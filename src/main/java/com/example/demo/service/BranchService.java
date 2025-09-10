package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.BranchDto;

public interface BranchService {
	
	// ---- Create ----
    BranchDto createBranch(Long clientId, BranchDto branchDto);
    List<BranchDto> createBranches(Long clientId, List<BranchDto> branchDtos);

    // ---- Update ----
    BranchDto updateBranch(Long branchId, BranchDto branchDto);

    // ---- Delete ----
    void deleteBranch(Long branchId);

    // ---- Get Single ----
    BranchDto getBranchById(Long branchId);

    // ---- Get Collections ----
    List<BranchDto> getBranchesByClient(Long clientId);
    List<BranchDto> getAllBranches();

    // ✅ New: Filtering by type
    List<BranchDto> getBranchesByType(String type);
    List<BranchDto> getBranchesByClientAndType(Long clientId, String type);
}
