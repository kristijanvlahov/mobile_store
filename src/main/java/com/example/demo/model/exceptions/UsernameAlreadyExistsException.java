package com.example.demo.model.exceptions;

public class UsernameAlreadyExistsException extends RuntimeException {
    public UsernameAlreadyExistsException(String username){
        super(String.format("Username with name %s already exists",username));
    }
}
