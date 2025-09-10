package com.example.demo.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.UserDto;
import com.example.demo.entity.Client;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.repository.ClientRepository;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;

@Service
@Transactional

public class UserServiceImpl  implements UserService {

	

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ClientRepository clientRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository,
                           ClientRepository clientRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.clientRepository = clientRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User createUser(UserDto dto, String rawPassword) {
        if (userRepository.existsByUsername(dto.getUsername())) {
            throw new IllegalArgumentException("Username already exists");
        }
        User u = new User();
        u.setUsername(dto.getUsername());
        u.setFullName(dto.getFullName());
        u.setPhone(dto.getPhone());
        u.setPassword(passwordEncoder.encode(rawPassword));

        if (dto.getClientId() != null) {
            Client c = clientRepository.findById(dto.getClientId())
                .orElseThrow(() -> new IllegalArgumentException("Client not found: " + dto.getClientId()));
            u.setClient(c);
        }

        Set<Role> roles = dto.getRoles() == null ? Set.of() :
            dto.getRoles().stream()
                .map(r -> roleRepository.findByName(r)
                        .orElseThrow(() -> new IllegalArgumentException("Role not found: " + r)))
                .collect(Collectors.toSet());
        u.setRoles(roles);

        return userRepository.save(u);
    }

    @Override
    public UserDto toDto(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setFullName(user.getFullName());
        dto.setPhone(user.getPhone());
        dto.setClientId(user.getClient() != null ? user.getClient().getId() : null);
        dto.setRoles(user.getRoles().stream().map(Role::getName).collect(Collectors.toSet()));
        return dto;
    }

    @Override
    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public UserDto getUserById(Long id) {
        User u = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found"));
        return toDto(u);
    }

    @Override
    public UserDto updateUser(Long id, UserDto dto) {
        User u = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found"));
        u.setFullName(dto.getFullName());
        u.setPhone(dto.getPhone());

        if (dto.getRoles() != null) {
            Set<Role> roles = dto.getRoles().stream()
                    .map(r -> roleRepository.findByName(r)
                            .orElseThrow(() -> new IllegalArgumentException("Role not found: " + r)))
                    .collect(Collectors.toSet());
            u.setRoles(roles);
        }

        if (dto.getClientId() != null) {
            Client c = clientRepository.findById(dto.getClientId())
                .orElseThrow(() -> new IllegalArgumentException("Client not found"));
            u.setClient(c);
        } else {
            u.setClient(null);
        }

        User saved = userRepository.save(u);
        return toDto(saved);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}

