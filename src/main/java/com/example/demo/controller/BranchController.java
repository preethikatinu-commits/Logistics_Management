package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import com.example.demo.dto.BranchDto;
import com.example.demo.entity.Branch;
import com.example.demo.service.BranchService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/branches")
@Tag(name = "Branch APIs", description = "CRUD APIs for managing Branches")
public class BranchController {

    private final BranchService branchService;

    public BranchController(BranchService branchService) {
        this.branchService = branchService;
    }

    // ✅ Create branch under a client
    @PostMapping("/client/{clientId}")
    @Operation(summary = "Create a new Branch for a Client")
    public ResponseEntity<BranchDto> createBranch(@PathVariable Long clientId,
                                                  @RequestBody BranchDto branchDto) {
        BranchDto saved = branchService.createBranch(clientId, branchDto);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    // ✅ Bulk create
    @PostMapping("/client/{clientId}/bulk")
    @Operation(summary = "Create multiple Branches for a Client")
    public ResponseEntity<List<BranchDto>> createBranches(@PathVariable Long clientId,
                                                          @RequestBody List<BranchDto> branchDtos) {
        return new ResponseEntity<>(branchService.createBranches(clientId, branchDtos), HttpStatus.CREATED);
    }

    // ✅ Update
    @PutMapping("/{branchId}")
    @Operation(summary = "Update a Branch by ID")
    public ResponseEntity<BranchDto> updateBranch(@PathVariable Long branchId,
                                                  @RequestBody BranchDto branchDto) {
        return ResponseEntity.ok(branchService.updateBranch(branchId, branchDto));
    }

    // ✅ Delete
    @DeleteMapping("/{branchId}")
    @Operation(summary = "Delete a Branch by ID")
    public ResponseEntity<Void> deleteBranch(@PathVariable Long branchId) {
        branchService.deleteBranch(branchId);
        return ResponseEntity.noContent().build();
    }

    // ✅ Get single branch
    @GetMapping("/{branchId}")
    @Operation(summary = "Get Branch by ID")
    public ResponseEntity<BranchDto> getBranch(@PathVariable Long branchId) {
        return ResponseEntity.ok(branchService.getBranchById(branchId));
    }

    // ✅ Get branches by client
    @GetMapping("/client/{clientId}")
    @Operation(summary = "Get all Branches for a Client")
    public ResponseEntity<List<BranchDto>> getBranchesByClient(@PathVariable Long clientId) {
        return ResponseEntity.ok(branchService.getBranchesByClient(clientId));
    }

    // ✅ Get branches by type
    @GetMapping("/type/{type}")
    @Operation(summary = "Get all Branches by Type (IMPORT/EXPORT/LOCAL)")
    public ResponseEntity<List<BranchDto>> getBranchesByType(@PathVariable String type) {
        return ResponseEntity.ok(branchService.getBranchesByType(type));
    }

    // ✅ Get by client + type
    @GetMapping("/client/{clientId}/type/{type}")
    @Operation(summary = "Get Branches for a Client filtered by Type")
    public ResponseEntity<List<BranchDto>> getBranchesByClientAndType(@PathVariable Long clientId,
                                                                      @PathVariable String type) {
        return ResponseEntity.ok(branchService.getBranchesByClientAndType(clientId, type));
    }

    // ✅ Get all
    @GetMapping
    @Operation(summary = "Get all Branches")
    public ResponseEntity<List<BranchDto>> getAllBranches() {
        return ResponseEntity.ok(branchService.getAllBranches());
    }
}