package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class AccessController {

	
	
	 @GetMapping("/access-denied")
	    public String accessDenied() {
	        return "accessDenied"; // resolves to /WEB-INF/views/accessDenied.jsp
	    }
}
