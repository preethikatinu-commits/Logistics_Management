package com.example.demo.controller.admin;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

	    // ✅ List branches with optional filters
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

	    // ✅ Show branch form
	    @GetMapping("/register")
	    public String showBranchForm(Model model) {
	        model.addAttribute("branch", new BranchDto());
	        model.addAttribute("clients", clientService.getAllClients());
	        model.addAttribute("types", BranchType.values());
	        return "branchRegister";
	    }

	    // ✅ Handle branch form submit
	    @PostMapping("/register")
	    public String saveBranch(@ModelAttribute("branch") BranchDto dto) {
	        branchService.createBranch(dto.getClientId(), dto);
	        return "redirect:/branches";
	    }

}
