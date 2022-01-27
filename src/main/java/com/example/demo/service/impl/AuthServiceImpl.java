package com.example.demo.service.impl;

import com.example.demo.model.User;
import com.example.demo.model.exceptions.InvalidArgumentsException;
import com.example.demo.model.exceptions.InvalidUsernameOrPasswordException;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.AuthService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;


    public AuthServiceImpl(UserRepository userRepository, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;

    }

    @Override
    public User login(String username, String password) {
        if (username==null || username.isEmpty() || password==null || password.isEmpty()) {
            throw new InvalidArgumentsException();
        }
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username,password);
        Authentication auth = authenticationManager.authenticate(token);
        SecurityContext sc = SecurityContextHolder.getContext();
        sc.setAuthentication(auth);
        User userDetails = (User) auth.getPrincipal();
        return userRepository.findByUsernameAndPassword(userDetails.getUsername(),userDetails.getPassword()).orElseThrow(InvalidUsernameOrPasswordException::new);
    }


    @Override
    public User getCurrentUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @Override
    public String getCurrentUserId() {
        return this.getCurrentUser().getUsername();
    }
}
