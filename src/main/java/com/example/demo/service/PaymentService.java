package com.example.demo.service;

import com.example.demo.model.dto.ChargeRequest;
import com.stripe.exception.*;
import com.stripe.model.Charge;

public interface PaymentService {
    Charge pay(ChargeRequest chargeRequest) throws CardException, APIException, AuthenticationException, InvalidRequestException, APIConnectionException;
    Charge chargeNewCard(String token, Double amount) throws Exception;
}
