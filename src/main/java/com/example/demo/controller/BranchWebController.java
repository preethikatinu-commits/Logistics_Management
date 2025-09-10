package com.example.demo.controller;

import java.util.List;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.BranchDto;
import com.example.demo.entity.BranchType;
import com.example.demo.service.BranchService;
import com.example.demo.service.ClientService;
@Controller
@RequestMapping("/branches")
public class BranchWebController {
	 private final BranchService branchService;
	    private final ClientService clientService;

	    public BranchWebController(BranchService branchService, ClientService clientService) {
	        this.branchService = branchService;
	        this.clientService = clientService;
	    }

	    // List branches (with optional filters)
	    @GetMapping
	    public String listBranches(@RequestParam(value = "clientId", required = false) Long clientId,
	                               @RequestParam(value = "type", required = false) String type,
	                               Model model) {
	        List<BranchDto> branches;
	        if (clientId != null && type != null && !type.isEmpty()) {
	            branches = branchService.getBranchesByClientAndType(clientId, type);
	        } else if (clientId != null) {
	            branches = branchService.getBranchesByClient(clientId);
	        } else if (type != null && !type.isEmpty()) {
	            branches = branchService.getBranchesByType(type);
	        } else {
	            branches = branchService.getAllBranches();
	        }

	        model.addAttribute("branches", branches);
	        model.addAttribute("clients", clientService.getAllClients());
	        model.addAttribute("types", BranchType.values());
	        model.addAttribute("selectedType", type);
	        model.addAttribute("selectedClientId", clientId);
	        return "branches";
	    }

	    // Show branch form
	    @GetMapping("/register")
	    public String showBranchForm(Model model) {
	        model.addAttribute("branch", new BranchDto());
	        model.addAttribute("clients", clientService.getAllClients());
	        model.addAttribute("types", BranchType.values());
	        return "branchRegister";
	    }

	    // Handle branch form submit with validation
	    @PostMapping("/register")
	    public String saveBranch(@Valid @ModelAttribute("branch") BranchDto dto,
	                             BindingResult bindingResult,
	                             Model model) {

	        // If validation errors, return to form and repopulate selects
	        if (bindingResult.hasErrors()) {
	            model.addAttribute("clients", clientService.getAllClients());
	            model.addAttribute("types", BranchType.values());
	            return "branchRegister";
	        }

	        try {
	            branchService.createBranch(dto.getClientId(), dto);
	        } catch (IllegalArgumentException ex) {
	            // business error from service (e.g., client not found)
	            model.addAttribute("error", ex.getMessage());
	            model.addAttribute("clients", clientService.getAllClients());
	            model.addAttribute("types", BranchType.values());
	            return "branchRegister";
	        } catch (Exception ex) {
	            model.addAttribute("error", "Unexpected error: " + ex.getMessage());
	            model.addAttribute("clients", clientService.getAllClients());
	            model.addAttribute("types", BranchType.values());
	            return "branchRegister";
	        }

	        return "redirect:/branches";
	    }
}
