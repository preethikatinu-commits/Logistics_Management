package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	  @GetMapping("/")
	    public String root() {
	        // Redirect root URL to /dashboard
	        return "redirect:/dashboard";
	    }

	    @GetMapping("/clients-home")
	    public String clientsHome() {
	        // This will resolve to /WEB-INF/views/clients.jsp
	        return "clients";
}
}