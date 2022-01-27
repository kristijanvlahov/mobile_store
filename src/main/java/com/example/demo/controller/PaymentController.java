package com.example.demo.controller;

import com.example.demo.model.ShoppingCart;
import com.example.demo.model.dto.ChargeRequest;
import com.example.demo.service.AuthService;
import com.example.demo.service.PaymentService;
import com.example.demo.service.ShoppingCartService;
import com.stripe.model.Charge;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/payments")
public class PaymentController {

    @Value("${STRIPE_P_KEY}")
    private String publicKey;

    private final ShoppingCartService shoppingCartService;
    private final AuthService authService;
    private final PaymentService paymentService;

    public PaymentController(ShoppingCartService shoppingCartService,
                             AuthService authService, PaymentService paymentService) {
        this.shoppingCartService = shoppingCartService;
        this.authService = authService;
        this.paymentService = paymentService;
    }

    @GetMapping("/charge")
    public String getCheckoutPage(ChargeRequest chargeRequest) {
            ShoppingCart shoppingCart = this.shoppingCartService.getActiveShoppingCart(this.authService.getCurrentUserId());
            return "checkout";

    }

    @PostMapping("/charge")
    public Charge checkout(@RequestHeader(value="token") String token, @RequestHeader(value="amount") Double amount) throws Exception {
            return this.paymentService.chargeNewCard(token,amount);
    }
}



