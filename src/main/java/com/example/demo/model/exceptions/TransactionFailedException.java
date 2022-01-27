package com.example.demo.model.exceptions;

import com.example.demo.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.PRECONDITION_FAILED)
public class TransactionFailedException extends RuntimeException {
    public TransactionFailedException(User userId, String message) {
        super(String.format("Transaction for user %s failed! Message: %s", userId, message));
    }

}
