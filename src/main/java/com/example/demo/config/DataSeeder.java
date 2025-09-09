package com.example.demo.config;


import com.example.demo.entity.Client;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.repository.ClientRepository;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.Set;

@Configuration
public class DataSeeder {


    private static final Logger log = LoggerFactory.getLogger(DataSeeder.class);

    @Bean
    public CommandLineRunner seed(RoleRepository roleRepository,
                                  UserRepository userRepository,
                                  ClientRepository clientRepository,
                                  PasswordEncoder passwordEncoder) {
        return args -> {
            log.info("➡️  Running DataSeeder: ensuring roles and default users exist...");

            // 1) Ensure roles exist
            Role rAdmin = roleRepository.findByName("ROLE_ADMIN")
                    .orElseGet(() -> roleRepository.save(new Role("ROLE_ADMIN")));
            Role rStaff = roleRepository.findByName("ROLE_STAFF")
                    .orElseGet(() -> roleRepository.save(new Role("ROLE_STAFF")));
            Role rEmp = roleRepository.findByName("ROLE_EMPLOYEE")
                    .orElseGet(() -> roleRepository.save(new Role("ROLE_EMPLOYEE")));
            Role rClient = roleRepository.findByName("ROLE_CLIENT")
                    .orElseGet(() -> roleRepository.save(new Role("ROLE_CLIENT")));

            log.info(" - Roles present: {}, {}, {}, {}",
                    rAdmin.getName(), rStaff.getName(), rEmp.getName(), rClient.getName());

            // 2) Create default admin user if missing
            final String adminUsername = "admin@demo.com";
            final String adminRawPassword = "Admin@123"; // change this immediately after first login
            if (!userRepository.existsByUsername(adminUsername)) {
                User admin = new User();
                admin.setUsername(adminUsername);
                admin.setFullName("System Admin");
                admin.setPassword(passwordEncoder.encode(adminRawPassword));
                admin.setRoles(Set.of(rAdmin));
                userRepository.save(admin);
                log.info("✅ Created default admin user: {} / {}", adminUsername, adminRawPassword);
            } else {
                Optional<User> existingAdmin = userRepository.findByUsername(adminUsername);
                existingAdmin.ifPresent(u -> log.info("ℹ️ Admin user already exists: {} (id={})", u.getUsername(), u.getId()));
            }

            // --- OPTIONAL: create a sample staff user (uncomment if needed) ---
            /*
            final String staffUsername = "staff@demo.com";
            if (!userRepository.existsByUsername(staffUsername)) {
                User staff = new User();
                staff.setUsername(staffUsername);
                staff.setFullName("Sample Staff");
                staff.setPassword(passwordEncoder.encode("Staff@123"));
                staff.setRoles(Set.of(rStaff));
                userRepository.save(staff);
                log.info("✅ Created sample staff user: {} / {}", staffUsername, "Staff@123");
            }
            */

            // --- OPTIONAL: create a sample client entry and link to a client-user ---
            /*
            final String clientEmail = "client@acme.com";
            if (!clientRepository.findByEmail(clientEmail).isPresent()) {
                Client client = new Client();
                client.setName("ACME Pvt Ltd");
                client.setEmail(clientEmail);
                client.setPhone("9999999999");
                clientRepository.save(client);
                log.info("✅ Created sample client: {} (id={})", client.getName(), client.getId());

                // create user for that client
                final String clientUser = "client.user@acme.com";
                if (!userRepository.existsByUsername(clientUser)) {
                    User cu = new User();
                    cu.setUsername(clientUser);
                    cu.setFullName("ACME Contact");
                    cu.setPassword(passwordEncoder.encode("Client@123"));
                    cu.setClient(client); // link client -> user
                    cu.setRoles(Set.of(rClient));
                    userRepository.save(cu);
                    log.info("✅ Created client-user: {} / {}", clientUser, "Client@123");
                }
            }
            */

            log.info("➡️  DataSeeder finished.");
        };
    }
}
