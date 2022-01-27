package com.example.demo.service;

import com.example.demo.model.Accessory;
import com.example.demo.model.Phone;
import com.example.demo.model.ShoppingCart;
import com.example.demo.model.User;
import com.example.demo.model.dto.ChargeRequest;

import java.util.List;
import java.util.Optional;

public interface ShoppingCartService {
    List<ShoppingCart> findAll();
    List<Phone> listAllProductsInShoppingCart(Long id);
    List<Accessory> listAllAccessoriesInShoppingCart(Long id);
    ShoppingCart getActiveShoppingCart(String username);
    Optional<ShoppingCart> save(String username, Long productId);
    ShoppingCart removeProductFromShoppingCart(String username,Long productId);
    ShoppingCart removeAccessoryFromShoppingCart(String username,Long productId);
    Optional<ShoppingCart> findById(Long id);
    List<Phone> findByUser (String user);
    List<Phone> listAllPhones (String user);
    List<Accessory> listAllAccessories (String user);
    Optional<ShoppingCart> addAccessory(String username, Long productId);
    ShoppingCart checkoutShoppingCart(User userId, ChargeRequest chargeRequest);
}
