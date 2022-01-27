package com.example.demo.controller;

import com.example.demo.model.Phone;
import com.example.demo.model.User;
import com.example.demo.model.dto.PhoneDto;
import com.example.demo.model.dto.UserDto;
import com.example.demo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/users")
public class UserRestController {
    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> findAll() {
        return this.userService.findAll();
    }

    @PostMapping("/add")
    public User save (@RequestBody UserDto userDto){
        return this.userService.register(userDto);

    }
}
