package com.example.demo.service.impl;

import com.example.demo.model.Accessory;
import com.example.demo.model.Category;
import com.example.demo.model.Manufacturer;
import com.example.demo.model.exceptions.CategoryNotFoundException;
import com.example.demo.model.exceptions.ManufacturerNotFoundException;
import com.example.demo.repository.AccessoryRepository;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.ManufacturerRepository;
import com.example.demo.service.AccessoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccessoryServiceImpl implements AccessoryService {

    private final AccessoryRepository accessoryRepository;
    private final ManufacturerRepository manufacturerRepository;
    private final CategoryRepository categoryRepository;

    public AccessoryServiceImpl(AccessoryRepository accessoryRepository, ManufacturerRepository manufacturerRepository, CategoryRepository categoryRepository) {
        this.accessoryRepository = accessoryRepository;
        this.manufacturerRepository = manufacturerRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Optional<Accessory> findById(Long id) {
        return this.accessoryRepository.findById(id);
    }

    @Override
    public List<Accessory> findAll() {
        return this.accessoryRepository.findAll();
    }

    @Override
    public Optional<Accessory> save(String name,String image,Double price,Long categoryId,Long manufacturerId) {
        Category category = this.categoryRepository.findById(categoryId)
                .orElseThrow(()->new CategoryNotFoundException(categoryId));
        Manufacturer manufacturer = this.manufacturerRepository.findById(manufacturerId)
                .orElseThrow(()->new ManufacturerNotFoundException(manufacturerId));
        return Optional.of(this.accessoryRepository.save(new Accessory(name,image,price,manufacturer,category)));
    }

    @Override
    public void deleteById(Long id) {
        this.accessoryRepository.deleteById(id);
    }
}
