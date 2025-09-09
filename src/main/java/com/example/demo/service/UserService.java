package com.example.demo.service;


import com.example.demo.dto.UserDto;
import com.example.demo.entity.User;
import java.util.List;
public interface UserService {
	
	
    User createUser(UserDto dto, String rawPassword);
	    UserDto toDto(User user);
	    List<UserDto> getAllUsers();
	    UserDto getUserById(Long id);
	    UserDto updateUser(Long id, UserDto dto);
	    void deleteUser(Long id);
	
	
}
