package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping("/")
    public String root() {
        return "redirect:/dashboard";
    }

    // This mapping handles requests to "/clients-home" instead of "/clients"
    // This avoids conflict with another controller which maps "/clients"
    @GetMapping("/clients-home")
    public String clientsHome() {
        // return a view named "clients" or use another name as per your template
        return "clients";
}
}