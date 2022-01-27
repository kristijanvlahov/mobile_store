package com.example.demo.service;

import com.example.demo.model.Phone;
import com.example.demo.model.dto.PhoneDto;

import java.util.List;
import java.util.Optional;

public interface PhoneService {
    List<Phone> findAll();
    Optional<Phone> findById(Long id);
    List<Phone> findByNameLike(String name);
    Optional<Phone> save (String name, Double price, String image, Integer weight, Double size,
                          String resolution, String OS, String cpu, String memory,
                          String camera, String battery,Long category, Long manufacturer);
    Optional<Phone> save (PhoneDto phoneDto);
    Optional<Phone> edit (Long id, String name,Double price, String image,Integer weight, Double size,
                          String resolution, String OS, String cpu, String memory,
                          String camera, String battery, Long category, Long manufacturer);
    Optional<Phone> edit (Long id, PhoneDto phoneDto);
    void deleteById(Long id);

}
