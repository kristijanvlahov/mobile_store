package com.example.demo.service.impl;

import com.example.demo.model.Role;
import com.example.demo.model.ShoppingCart;
import com.example.demo.model.User;
import com.example.demo.model.dto.UserDto;
import com.example.demo.model.exceptions.*;
import com.example.demo.repository.ShoppingCartRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ShoppingCartRepository shoppingCartRepository;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, ShoppingCartRepository shoppingCartRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.shoppingCartRepository = shoppingCartRepository;
    }


    @Override
    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    @Override
    public User register(UserDto userDto) {
        if (userDto.getUsername()==null || userDto.getUsername().isEmpty()  || userDto.getPassword()==null || userDto.getPassword().isEmpty())
            throw new InvalidUsernameOrPasswordException();
        if(this.userRepository.findByUsername(userDto.getUsername()).isPresent())
            throw new UsernameAlreadyExistsException(userDto.getUsername());
        User user = new User(userDto.getUsername(),passwordEncoder.encode(userDto.getPassword()),userDto.getName(), userDto.getSurname(), userDto.getEmail(), userDto.getAddress(), userDto.getRole());
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        return this.userRepository.findByUsername(username).orElseThrow(()->new UsernameNotFoundException(username));
    }


}
