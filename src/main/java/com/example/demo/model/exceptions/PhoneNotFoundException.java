package com.example.demo.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class PhoneNotFoundException extends RuntimeException{
    public PhoneNotFoundException(Long id){
        super(String.format("Product with id: %d is not found",id));
    }
}
