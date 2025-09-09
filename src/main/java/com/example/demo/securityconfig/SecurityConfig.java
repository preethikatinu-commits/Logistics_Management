package com.example.demo.securityconfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.service.CustomUserDetailsService;

@Configuration

public class SecurityConfig {
	 private final CustomUserDetailsService customUserDetailsService;

	    public SecurityConfig(CustomUserDetailsService customUserDetailsService) {
	        this.customUserDetailsService = customUserDetailsService;
	    }

	    @Bean
	    public PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }

	    @Bean
	    public UserDetailsService userDetailsService() {
	        return customUserDetailsService;
	    }

	    @Bean
	    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	        // Keep CSRF enabled (we add CSRF tokens in JSP forms)
	        http.csrf().and();

	        // Use explicit AntPathRequestMatcher instances to avoid the "ambiguous matcher" error
	        http.authorizeHttpRequests()
	            .requestMatchers(new AntPathRequestMatcher("/api/admin/**")).hasRole("ADMIN")
	            .requestMatchers(new AntPathRequestMatcher("/staff/**")).hasRole("STAFF")
	            .requestMatchers(new AntPathRequestMatcher("/client/**")).hasRole("CLIENT")
	            .requestMatchers(
	                new AntPathRequestMatcher("/login"),
	                new AntPathRequestMatcher("/perform_login"),
	                new AntPathRequestMatcher("/css/**"),
	                new AntPathRequestMatcher("/js/**"),
	                new AntPathRequestMatcher("/images/**")
	            ).permitAll()
	            .anyRequest().authenticated();

	        http.formLogin(form -> form
	                .loginPage("/login")
	                .loginProcessingUrl("/perform_login")
	                .defaultSuccessUrl("/dashboard", true)
	                .permitAll()
	        );

	        http.logout(logout -> logout
	                .logoutUrl("/logout")
	                .logoutSuccessUrl("/login?logout")
	                .permitAll()
	        );

	        return http.build();
	    }
}