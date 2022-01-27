package com.example.demo.service;

import com.example.demo.model.Manufacturer;

import java.util.List;
import java.util.Optional;

public interface ManufacturerService {
    Optional<Manufacturer> findById(Long id);
    List<Manufacturer> findAll();
    Optional<Manufacturer> save(String name, String country);
    void deleteById(Long id);
}
