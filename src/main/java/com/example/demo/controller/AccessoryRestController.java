package com.example.demo.controller;

import com.example.demo.model.Accessory;
import com.example.demo.service.AccessoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/accessories")
public class AccessoryRestController {
    private final AccessoryService accessoryService;

    public AccessoryRestController(AccessoryService accessoryService) {
        this.accessoryService = accessoryService;
    }

    @GetMapping
    public List<Accessory> findAll() { return this.accessoryService.findAll();}

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteById (@PathVariable Long id){
        this.accessoryService.deleteById(id);
        if(!this.accessoryService.findById(id).isPresent()) return ResponseEntity.ok().build();
        return ResponseEntity.badRequest().build();

    }
}
