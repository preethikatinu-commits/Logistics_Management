package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import org.springframework.web.servlet.view.InternalResourceViewResolver;

@SpringBootApplication
public class LogisticsMagmtApplication {

    public static void main(String[] args) {
        SpringApplication.run(LogisticsMagmtApplication.class, args);
    }
   
    @Bean
    public InternalResourceViewResolver jspViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        return resolver;
    }
    
    
}
