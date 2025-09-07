package com.example.demo.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.demo.dto.ClientDto;
import com.example.demo.service.ClientService;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/clients")
public class ClientWebController {

	 @Autowired
	    private ClientService clientService;

	    // show register form
	    @GetMapping("/register")
	    public String showClientForm(Model model) {
	        if (!model.containsAttribute("client")) {
	            model.addAttribute("client", new ClientDto());
	        }
	        return "clientRegister"; // resolves to src/main/resources/templates/clientRegister.html
	    }

	    // handle form submit
	    @PostMapping("/register")
	    public String registerClient(@Valid @ModelAttribute("client") ClientDto clientDto,
	                                 BindingResult bindingResult, Model model) {

	        if (bindingResult.hasErrors()) {
	            // validation errors — redisplay form (errors available in template)
	            return "clientRegister";
	        }

	        try {
	            clientService.createClient(clientDto);
	            model.addAttribute("message", "Client registered successfully!");
	            model.addAttribute("client", new ClientDto()); // clear the form
	        } catch (IllegalArgumentException ex) {
	            // example: uniqueness or business rule failed
	            model.addAttribute("error", ex.getMessage());
	            model.addAttribute("client", clientDto);
	            return "clientRegister";
	        } catch (Exception ex) {
	            model.addAttribute("error", "Unexpected error: " + ex.getMessage());
	            model.addAttribute("client", clientDto);
	            return "clientRegister";
	        }

	        return "clientRegister";
	    }

	    // list clients
	    @GetMapping
	    public String listClients(Model model) {
	        List<ClientDto> clients = clientService.getAllClients();
	        model.addAttribute("clients", clients);
	        return "clients"; // resolves to src/main/resources/templates/clients.html
	    }
	    
	 // show edit form
	    @GetMapping("/edit/{id}")
	    public String showEditForm(@PathVariable("id") Long id, Model model) {
	        ClientDto client = clientService.getClientById(id);
	        if (client == null) {
	            model.addAttribute("error", "Client not found");
	            return "redirect:/clients";
	        }
	        model.addAttribute("client", client);
	        return "clientEdit"; // resolves to clientEdit.jsp
	    }

	    // handle edit submit
	    @PostMapping("/edit/{id}")
	    public String updateClient(@PathVariable("id") Long id,
	                               @Valid @ModelAttribute("client") ClientDto clientDto,
	                               BindingResult bindingResult,
	                               Model model) {
	        if (bindingResult.hasErrors()) {
	            return "clientEdit";
	        }
	        try {
	            clientService.updateClient(id, clientDto);
	            model.addAttribute("message", "Client updated successfully!");
	        } catch (IllegalArgumentException ex) {
	            model.addAttribute("error", ex.getMessage());
	            model.addAttribute("client", clientDto);
	            return "clientEdit";
	        } catch (Exception ex) {
	            model.addAttribute("error", "Unexpected error: " + ex.getMessage());
	            model.addAttribute("client", clientDto);
	            return "clientEdit";
	        }
	        return "redirect:/clients";
	    }

	    // delete client (simple GET flow with confirm in UI)
	    @GetMapping("/delete/{id}")
	    public String deleteClient(@PathVariable("id") Long id, Model model) {
	        try {
	            clientService.deleteClient(id);
	        } catch (Exception ex) {
	            model.addAttribute("error", "Unable to delete: " + ex.getMessage());
	            return "redirect:/clients";
	        }
	        return "redirect:/clients";
	    }
	}


