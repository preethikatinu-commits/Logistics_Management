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
        // keep CSRF enabled
        http.csrf().and();

        http.authorizeHttpRequests()
            // API / admin paths
            .requestMatchers(new AntPathRequestMatcher("/api/admin/**")).hasRole("ADMIN")
            .requestMatchers(new AntPathRequestMatcher("/staff/**")).hasRole("STAFF")
            .requestMatchers(new AntPathRequestMatcher("/client/**")).hasRole("CLIENT")

            // public pages & static resources (allow register pages too)
            .requestMatchers(
                new AntPathRequestMatcher("/login"),
                new AntPathRequestMatcher("/perform_login"),
                new AntPathRequestMatcher("/branches/register"),
                new AntPathRequestMatcher("/register"),
                new AntPathRequestMatcher("/css/**"),
                new AntPathRequestMatcher("/js/**"),
                new AntPathRequestMatcher("/images/**"),
                new AntPathRequestMatcher("/webjars/**"),
                new AntPathRequestMatcher("/error")
            ).permitAll()

            // everything else requires authentication
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

        // optional: simple access-denied handler page
        http.exceptionHandling()
            .accessDeniedPage("/access-denied");

        return http.build();
    }
}