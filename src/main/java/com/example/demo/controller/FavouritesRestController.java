package com.example.demo.controller;

import com.example.demo.model.Favourites;
import com.example.demo.model.Phone;
import com.example.demo.model.dto.FavouritesDto;
import com.example.demo.model.dto.PhoneDto;
import com.example.demo.model.dto.UserDto;
import com.example.demo.service.FavouritesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/favourites")
public class FavouritesRestController {
    private final FavouritesService favouritesService;

    public FavouritesRestController(FavouritesService favouritesService) {
        this.favouritesService = favouritesService;
    }

    @GetMapping
    public List<Favourites> findAll() {
        return this.favouritesService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Favourites> findById (@PathVariable Long id){
        return this.favouritesService.findById(id)
                .map(favourites -> ResponseEntity.ok().body(favourites))
                .orElseGet(()-> ResponseEntity.badRequest().build());
    }

    @PostMapping("/add")
    public ResponseEntity<Favourites> save (@RequestBody FavouritesDto favouritesDto){
        return this.favouritesService.save(favouritesDto.getPhone(),favouritesDto.getUsername())
                .map(favourites -> ResponseEntity.ok().body(favourites))
                .orElseGet(()-> ResponseEntity.badRequest().build());
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteById (@PathVariable Long id){
        this.favouritesService.deleteById(id);
        if(!this.favouritesService.findById(id).isPresent()) return ResponseEntity.ok().build();
        return ResponseEntity.badRequest().build();

    }

}
