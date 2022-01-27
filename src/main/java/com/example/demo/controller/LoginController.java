package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.model.dto.LoginDto;
import com.example.demo.service.AuthService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/login")
public class LoginController {

    private final AuthService authService;

    public LoginController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping
    public User login(@RequestBody LoginDto loginDto) {
            User user = this.authService.login(loginDto.getUsername(),loginDto.getPassword());
            return user;
    }

}
