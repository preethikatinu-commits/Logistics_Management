package com.example.demo.controller;



import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

	@GetMapping("/dashboard")
    public String dashboard(Authentication auth, Model model) {
        if (auth == null) return "redirect:/login";

        boolean isAdmin = auth.getAuthorities().stream()
               .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
        if (isAdmin) return "admin/dashboard";   // admin JSP
        boolean isStaff = auth.getAuthorities().stream()
               .anyMatch(a -> a.getAuthority().equals("ROLE_STAFF"));
        if (isStaff) return "staff/dashboard";   // staff JSP
        return "user/dashboard";                  // default
    }
	}


