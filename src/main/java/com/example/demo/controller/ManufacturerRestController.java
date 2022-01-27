package com.example.demo.controller;

import com.example.demo.model.Manufacturer;
import com.example.demo.service.ManufacturerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/manufacturers")
public class ManufacturerRestController {

    private final ManufacturerService manufacturerService;

    public ManufacturerRestController(ManufacturerService manufacturerService) {
        this.manufacturerService = manufacturerService;
    }

    @GetMapping
    public List<Manufacturer> findAll(){
        return this.manufacturerService.findAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Manufacturer> findById(@PathVariable Long id){
        return this.manufacturerService.findById(id)
                .map(manufacturer -> ResponseEntity.ok().body(manufacturer))
                .orElseGet(()-> ResponseEntity.badRequest().build());
    }
    @PostMapping("/add")
    public ResponseEntity<Manufacturer> save (@RequestParam String name,@RequestParam String country){
        return this.manufacturerService.save(name,country)
                .map(manufacturer -> ResponseEntity.ok().body(manufacturer))
                .orElseGet(()-> ResponseEntity.badRequest().build());
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteById(@PathVariable Long id) {
        this.manufacturerService.deleteById(id);
        if(this.manufacturerService.findById(id).isPresent()) return ResponseEntity.ok().build();
        return ResponseEntity.badRequest().build();
    }

}
