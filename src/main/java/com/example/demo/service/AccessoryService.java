package com.example.demo.service;

import com.example.demo.model.Accessory;
import com.example.demo.model.Manufacturer;

import java.util.List;
import java.util.Optional;

public interface AccessoryService {
    Optional<Accessory> findById(Long id);
    List<Accessory> findAll();
    Optional<Accessory> save(String name, String image,Double price,Long categoryId,Long manufacturerId);
    void deleteById(Long id);
}
