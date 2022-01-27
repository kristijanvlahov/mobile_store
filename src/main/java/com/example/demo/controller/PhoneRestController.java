package com.example.demo.controller;

import com.example.demo.model.Phone;
import com.example.demo.model.dto.PhoneDto;
import com.example.demo.service.PhoneService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/phones")
public class PhoneRestController {
    private final PhoneService phoneService;

    public PhoneRestController(PhoneService phoneService) {
        this.phoneService = phoneService;
    }

    @GetMapping
    public List<Phone> findAll() {
        return this.phoneService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Phone> findById (@PathVariable Long id){
        return this.phoneService.findById(id)
                .map(phone -> ResponseEntity.ok().body(phone))
                .orElseGet(()-> ResponseEntity.badRequest().build());
    }

    @GetMapping("/search/{name}")
    public List<Phone> findByName (@PathVariable String name){
        return this.phoneService.findByNameLike(name);
    }

    @PostMapping("/add")
    public ResponseEntity<Phone> save (@RequestBody PhoneDto phoneDto){
        return this.phoneService.save(phoneDto)
                .map(phone -> ResponseEntity.ok().body(phone))
                .orElseGet(()-> ResponseEntity.badRequest().build());
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Phone> edit (@PathVariable Long id, @RequestBody PhoneDto phoneDto){
        return this.phoneService.edit(id,phoneDto)
                .map(phone -> ResponseEntity.ok().body(phone))
                .orElseGet(()-> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteById (@PathVariable Long id){
        this.phoneService.deleteById(id);
        if(!this.phoneService.findById(id).isPresent()) return ResponseEntity.ok().build();
        return ResponseEntity.badRequest().build();

    }
}
