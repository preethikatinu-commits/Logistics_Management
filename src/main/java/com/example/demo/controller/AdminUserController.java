package com.example.demo.controller;


import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.UserDto;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/api/admin/users")

public class AdminUserController {

	
	

    private final UserService userService;
    public AdminUserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto dto, @RequestParam String password) {
        User created = userService.createUser(dto, password);
        return ResponseEntity.created(URI.create("/api/admin/users/" + created.getId()))
                .body(userService.toDto(created));
    }

    @GetMapping
    public List<UserDto> allUsers() { return userService.getAllUsers(); }

    @GetMapping("/{id}")
    public UserDto get(@PathVariable Long id) { return userService.getUserById(id); }

    @PutMapping("/{id}")
    public UserDto update(@PathVariable Long id, @RequestBody UserDto dto) { return userService.updateUser(id, dto); }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
