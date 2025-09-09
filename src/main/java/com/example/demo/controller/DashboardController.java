package com.example.demo.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

	
	@GetMapping("/dashboard")
    public String dashboard(Authentication auth) {
        if (auth == null) {
            return "redirect:/login";
        }
        // Log authorities for debugging
        log.info("Authenticated user: {} authorities={}", auth.getName(), auth.getAuthorities());

	// Check roles and return correct JSP
    if (auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
        return "adminhome"; // /WEB-INF/views/adminhome.jsp
    }
    if (auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_STAFF"))) {
        return "staffhome"; // /WEB-INF/views/staffhome.jsp
    }
    if (auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_CLIENT"))) {
        return "clienthome"; // /WEB-INF/views/clienthome.jsp
    }

    // Default dashboard
    return "dashboard"; // /WEB-INF/views/dashboard.jsp
}
	}


