package com.example.demo.controller;

import com.example.demo.model.Accessory;
import com.example.demo.model.Phone;
import com.example.demo.model.ShoppingCart;
import com.example.demo.model.dto.AccessoryDto;
import com.example.demo.model.dto.PhoneDto;
import com.example.demo.model.dto.ShoppingCartDto;
import com.example.demo.service.ShoppingCartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/carts")
public class ShoppingCartRestController {
    private final ShoppingCartService shoppingCartService;

    public ShoppingCartRestController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @GetMapping
    public List<ShoppingCart> findAll() {
        return this.shoppingCartService.findAll();
    }

    @GetMapping("/{id}")
    public List<Phone> findById (@PathVariable Long id){
        return this.shoppingCartService.listAllProductsInShoppingCart(id);
    }

    @GetMapping("/phones/{name}")
    public List<Phone> findByName (@PathVariable String name){
        return this.shoppingCartService.listAllPhones(name);
    }

    @GetMapping("/accessory/{name}")
    public List<Accessory> findByAccessoryName (@PathVariable String name){
        return this.shoppingCartService.listAllAccessories(name);
    }

    @PostMapping("/add")
    public ResponseEntity<ShoppingCart> addProductToShoppingCart (@RequestBody ShoppingCartDto shoppingCartDto){
        return this.shoppingCartService.save(shoppingCartDto.getUsername(),shoppingCartDto.getPhone())
                .map(shoppingCart -> ResponseEntity.ok().body(shoppingCart))
                .orElseGet(()-> ResponseEntity.badRequest().build());

    }

    @PostMapping("/add/accessory")
    public ResponseEntity<ShoppingCart> addAccessoryToShoppingCart (@RequestBody AccessoryDto accessoryDto){
        return this.shoppingCartService.addAccessory(accessoryDto.getUsername(),accessoryDto.getAccessory())
                .map(shoppingCart -> ResponseEntity.ok().body(shoppingCart))
                .orElseGet(()-> ResponseEntity.badRequest().build());

    }

    @DeleteMapping("/delete/{name}/{id}")
    public ResponseEntity<ShoppingCart> removeProductFromShoppingCart (@PathVariable String name, @PathVariable Long id){
        this.shoppingCartService.removeProductFromShoppingCart(name,id);
        if(!this.shoppingCartService.findById(id).isPresent()) return ResponseEntity.ok().build();
        return ResponseEntity.badRequest().build();

    }

    @DeleteMapping("/delete/accessory/{name}/{id}")
    public ResponseEntity<ShoppingCart> removeAccessoryFromShoppingCart (@PathVariable String name, @PathVariable Long id){
        this.shoppingCartService.removeAccessoryFromShoppingCart(name,id);
        if(!this.shoppingCartService.findById(id).isPresent()) return ResponseEntity.ok().build();
        return ResponseEntity.badRequest().build();

    }



}
