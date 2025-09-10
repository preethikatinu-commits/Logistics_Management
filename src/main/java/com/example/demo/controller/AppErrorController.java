package com.example.demo.controller;




import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.Map;
@Controller
public class AppErrorController {

	private final ErrorAttributes errorAttributes;

    public AppErrorController(ErrorAttributes errorAttributes) {
        this.errorAttributes = errorAttributes;
    }

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        // Wrap HttpServletRequest into a WebRequest (ServletWebRequest)
        ServletWebRequest webRequest = new ServletWebRequest(request);

        // Request detailed error attributes (status, error, message, path)
        Map<String, Object> attrs = errorAttributes.getErrorAttributes(
            webRequest,
            ErrorAttributeOptions.of(
                ErrorAttributeOptions.Include.MESSAGE,
                ErrorAttributeOptions.Include.EXCEPTION,
                ErrorAttributeOptions.Include.BINDING_ERRORS
            )
        );

        model.addAttribute("status", attrs.get("status"));
        model.addAttribute("error", attrs.get("error"));
        model.addAttribute("message", attrs.get("message"));
        model.addAttribute("path", attrs.get("path"));
        return "error"; // resolves to /WEB-INF/views/error.jsp
    }
}