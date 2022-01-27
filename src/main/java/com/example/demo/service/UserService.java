package com.example.demo.service;

import com.example.demo.model.Phone;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.model.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService {
    List<User> findAll();
    User register(UserDto userDto);
}
