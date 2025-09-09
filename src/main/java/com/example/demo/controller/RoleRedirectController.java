package com.example.demo.controller;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RoleRedirectController {
	
	 @GetMapping("/roleDashboard")
	    public String roleDashboard(Authentication authentication) {
	        for (GrantedAuthority auth : authentication.getAuthorities()) {
	            String role = auth.getAuthority();
	            if (role.equals("ROLE_ADMIN")) {
	                return "redirect:/admin/dashboard";
	            } else if (role.equals("ROLE_STAFF")) {
	                return "redirect:/staff/dashboard";
	            } else if (role.equals("ROLE_CLIENT")) {
	                return "redirect:/client/dashboard";
	            }
	        }
	        return "redirect:/login?error";
	    }

}
