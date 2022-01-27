package com.example.demo.service;

import com.example.demo.model.Accessory;
import com.example.demo.model.Favourites;
import com.example.demo.model.Phone;

import java.util.List;
import java.util.Optional;

public interface FavouritesService {
    List<Favourites> findAll();
    Optional<Favourites> findById(Long id);
    Optional<Favourites> save(Long phoneId, String username);
    void deleteById(Long id);
}
